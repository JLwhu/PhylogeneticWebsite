/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 11, 2013
 */
package web.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pal.tree.Node;
import pal.tree.NodeUtils;
import pal.tree.SimpleTree;
import pal.tree.Tree;
import pal.tree.TreeParseException;
import phyloGeneticAnalysis.Exception.nameFormatException;
import phyloGeneticAnalysis.Util.RootedTreeSubtreeUtils;
import phyloGeneticAnalysis.Util.TreeUtilsAdded;
import phyloGeneticAnalysis.Util.speciesNameFormatUtils;
import phyloGeneticAnalysis.io.CsvFileIO;
import phyloGeneticAnalysis.io.NewickFileIO;
import web.dao.MongoSpeciesNameDao;
import web.dao.SpeciesNameDao;
import web.dao.impl.MongoSpeciesNameDaoImpl;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.MongoSpeciesName;
import web.service.SubtreeExtractionService;

public class SubtreeExtractionServiceImpl 
		implements  SubtreeExtractionService {
	
	private Tree mainTree;
	private Tree subtree;

	
	/* (non-Javadoc)
	 * @see web.service.SubtreeExtractionService#subtreeExtraction(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void subtreeExtraction(String subTreeNamelistFile,
			String treFileName, String nameMappingFileName,
			String resOutTreFileName, String mainTreeImageFileName,
			String subTreeImageFielName, String nameChoice, String inputChoice)
			throws Exception {

		SimpleTree m_palTree;
		List<String> subtreeNameList;
		List<String> subSciNl = null;
		List<String> subComNl = null;
		subSciNl = new ArrayList<String>();
		subComNl = new ArrayList<String>();
		Tree ret_subtree;

		NewickFileIO nfio = new NewickFileIO();
		PalTreeServiceImpl paltreeService = new PalTreeServiceImpl();		
		HashMap sciToCommonNameMap = getAllSpeciesSciToCommonNameMap(nameMappingFileName);
		HashMap commonToSciNameMap = getCommonToSciNameMap(sciToCommonNameMap);
		
		CsvFileIO csvf = new CsvFileIO();
		switch (inputChoice) {
		case "1": // ebird science name
			subSciNl = csvf.readCSVcolumn(subTreeNamelistFile, 1, true);
			subSciNl = speciesNameFormatUtils.nameListFormat(subSciNl);
			break;
		case "2": // ebird common name
			subComNl = csvf.readCSVcolumn(subTreeNamelistFile, 1, true);
			subComNl = speciesNameFormatUtils.nameListFormat(subComNl);
			subSciNl = getSciFromCommonNameList(subComNl,commonToSciNameMap);
			break;
		case "3": // ebird science & common name
			subtreeNameList = csvf.readCSVcolumn(subTreeNamelistFile, 1, true);
			speciesNameFormatUtils.nameListFormat(subtreeNameList, subComNl,
					subSciNl);
			break;
		case "4": // plain txt science name
			subSciNl = csvf.readCSVcolumn(subTreeNamelistFile, 0, false);
			subSciNl = speciesNameFormatUtils.nameListFormat(subSciNl);
			break;
		case "5": // plain txt common name
			subComNl = csvf.readCSVcolumn(subTreeNamelistFile, 0, false);
			subComNl = speciesNameFormatUtils.nameListFormat(subComNl);
			subSciNl = getSciFromCommonNameList(subComNl,commonToSciNameMap);
			break;
		}		

		RootedTreeSubtreeUtils rtsu = new RootedTreeSubtreeUtils();
		m_palTree = (SimpleTree) nfio.inputNewickFile(treFileName);
		ret_subtree = rtsu.getSpecifiedSubTree(m_palTree, subSciNl);
		mainTree = m_palTree;
		subtree = ret_subtree;
		if (nameChoice.equals("2")){
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
			treeScientificNameTranslate(mainTree,sciToCommonNameMap);
			treeScientificNameTranslate(subtree,sciToCommonNameMap);
		}else{
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
		}

		paltreeService.setTree(mainTree);
		paltreeService.graphicsGeneration(mainTree,mainTreeImageFileName);
		
		paltreeService.setTree(subtree);
		paltreeService.graphicsGeneration(subtree,subTreeImageFielName);


	}
	
	public void subtreeExtractionId(List<Integer> subTreeSpeciesIdlist,
			String treFileName, String nameMappingFileName,
			String resOutTreFileName, String subTreeImageFielName,
			String nameChoice) throws Exception {
		SimpleTree m_palTree;

		Tree ret_subtree;

		NewickFileIO nfio = new NewickFileIO();
		PalTreeServiceImpl paltreeService = new PalTreeServiceImpl();

		RootedTreeSubtreeUtils rtsu = new RootedTreeSubtreeUtils();
		m_palTree = (SimpleTree) nfio.inputNewickFile(treFileName);
		HashMap nameMap = getAllSpeciesSciToCommonNameMap(nameMappingFileName);
		List subTreeNamelist= new ArrayList<String>();
		
		for (int i=0;i<subTreeSpeciesIdlist.size();i++){
			String name;
		//	if (nameChoice.equals("2")){
		//		name = ((MongoSpeciesName) nameMap.get(subTreeSpeciesIdlist.get(i))).getCommonName();
		//	}else{
				name = ((MongoSpeciesName) nameMap.get(subTreeSpeciesIdlist.get(i))).getScientificName();
		//	}
			subTreeNamelist.add(name);
		}
		ret_subtree = rtsu.getSpecifiedSubTree(m_palTree, subTreeNamelist);
		mainTree = m_palTree;
		subtree = ret_subtree;
		if (nameChoice.equals("2")){
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
			treeScientificNameTranslate(mainTree,nameMap);
			treeScientificNameTranslate(subtree,nameMap);
		}else{
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
		}
		paltreeService.setTree(subtree);
		paltreeService.graphicsGeneration(subtree,subTreeImageFielName);


	}

	public void subtreeExtraction(List<String> subTreeNamelist,
			String treFileName,String nameMappingFileName, String resOutTreFileName,  String subTreeImageFielName,String nameChoice)
			throws Exception {

		SimpleTree m_palTree;

		Tree ret_subtree;

		NewickFileIO nfio = new NewickFileIO();
		PalTreeServiceImpl paltreeService = new PalTreeServiceImpl();
		
		HashMap nameMap = getAllSpeciesSciToCommonNameMap(nameMappingFileName);

		RootedTreeSubtreeUtils rtsu = new RootedTreeSubtreeUtils();
		m_palTree = (SimpleTree) nfio.inputNewickFile(treFileName);

		ret_subtree = rtsu.getSpecifiedSubTree(m_palTree, subTreeNamelist);
		mainTree = m_palTree;
		subtree = ret_subtree;
		if (nameChoice.equals("2")){
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
			treeScientificNameTranslate(mainTree,nameMap);
			treeScientificNameTranslate(subtree,nameMap);
		}else{
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
		}
		paltreeService.setTree(subtree);
		paltreeService.graphicsGeneration(subtree,subTreeImageFielName);


	}
	
	public void treeScientificNameTranslate(Tree tree, HashMap nameMap) throws Exception{
	//	HashMap nameMap = (HashMap)speciesNameDao.getAllSpeciesNames();
	//	HashMap nameMap = getAllSpeciesNameMap();
		Node node = tree.getRoot();
		Node root = node;
		do {
			node = NodeUtils.postorderSuccessor(node);
			if (node.isLeaf()){
				String scientificName = node.getIdentifier().getName();
				String commonName =  (String) nameMap.get(scientificName);
				if (commonName!=null && commonName!="")
					node.getIdentifier().setName(commonName);
				}
		} while (node != root);
	}
	
	public double getMainTreeDiversity() throws Exception{
		if (mainTree!=null)
			return TreeUtilsAdded.computeDiversity(mainTree);
		else
			return 0;
	}
	
	public double getSubTreeDiversity() throws Exception{
		if (subtree!=null)
			return TreeUtilsAdded.computeDiversity(subtree);
		else
			return 0;
	}
	
	public HashMap getAllSpeciesSciToCommonNameMap(String mappingFileName) throws Exception{
		HashMap mapFile = new HashMap();

		CsvFileIO csvf = new CsvFileIO();
		List sciNameList = csvf.readCSVcolumn(mappingFileName, 0, false);
		List commonNameList = csvf.readCSVcolumn(mappingFileName, 1, false);
		for (int i=0;i<sciNameList.size();i++){
			String sciName = (String) sciNameList.get(i);
		//	sciName.toLowerCase();
			String commonName = (String) commonNameList.get(i);
		//	commonName.toLowerCase();
			mapFile.put(sciName,commonName);
		}		
		return mapFile;
	}
	
	public HashMap getCommonToSciNameMap(HashMap sciToCommonNameMap) throws Exception{
		HashMap commonToSciMap = new HashMap();
		Iterator it = sciToCommonNameMap.keySet().iterator();
		while(it.hasNext()){
			String sciName = (String) it.next();
			String commonName = (String) sciToCommonNameMap.get(sciName);
			commonToSciMap.put(commonName, sciName);
		}
		return commonToSciMap;
	}
	public ArrayList getSciFromCommonNameList(List commonNL, HashMap commonToSciMap) throws Exception{
		ArrayList sciNameList = new ArrayList();
		for (int i = 0;i<commonNL.size();i++){
			String commonName = (String) commonNL.get(i);
			String sciName = (String) commonToSciMap.get(commonName);
			sciNameList.add(sciName);
		}
		return sciNameList;
	}
}
