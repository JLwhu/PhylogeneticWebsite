/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 11, 2013
 */
package web.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import web.service.SubtreeExtractionDBService;

public class SubtreeExtractionDBServiceImpl 
		implements  SubtreeExtractionDBService {
	
	private Tree mainTree;
	private Tree subtree;
	private SpeciesNameDao speciesNameDao;
	private MongoSpeciesNameDao mongoSpeciesNameDao=new MongoSpeciesNameDaoImpl();
	
	public SpeciesNameDao getSpeciesNameDao() {
		return speciesNameDao;
	}
	public void setSpeciesNameDao(SpeciesNameDao speciesNameDao) {
		this.speciesNameDao = speciesNameDao;
	}
	
	/* (non-Javadoc)
	 * @see web.service.SubtreeExtractionService#subtreeExtraction(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void subtreeExtraction(String subTreeNamelistFile,
			String treFileName, String resOutTreFileName, String mainTreeImageFileName, String subTreeImageFielName,String nameChoice)
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

		CsvFileIO csvf = new CsvFileIO();
		subtreeNameList = csvf.readCSVcolumn(subTreeNamelistFile, 1, true);
		speciesNameFormatUtils.nameListFormat(subtreeNameList, subComNl,
				subSciNl);
		RootedTreeSubtreeUtils rtsu = new RootedTreeSubtreeUtils();
		m_palTree = (SimpleTree) nfio.inputNewickFile(treFileName);
		ret_subtree = rtsu.getSpecifiedSubTree(m_palTree, subSciNl);
		mainTree = m_palTree;
		subtree = ret_subtree;
		if (nameChoice.equals("2")){
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
			treeScientificNameTranslate(mainTree);
			treeScientificNameTranslate(subtree);
		}else{
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
		}

		paltreeService.setTree(mainTree);
		paltreeService.graphicsGeneration(mainTree,mainTreeImageFileName);
		
		paltreeService.setTree(subtree);
		paltreeService.graphicsGeneration(subtree,subTreeImageFielName);


	}
	
	public void subtreeExtractionId(List<Integer> subTreeSpeciesIdlist,
			String treFileName, String resOutTreFileName,  String subTreeImageFielName,String nameChoice)
			throws Exception {
		SimpleTree m_palTree;

		Tree ret_subtree;

		NewickFileIO nfio = new NewickFileIO();
		PalTreeServiceImpl paltreeService = new PalTreeServiceImpl();

		RootedTreeSubtreeUtils rtsu = new RootedTreeSubtreeUtils();
		m_palTree = (SimpleTree) nfio.inputNewickFile(treFileName);
		HashMap nameMap = (HashMap)mongoSpeciesNameDao.getAllSpeciesNames();
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
			treeScientificNameTranslate(mainTree);
			treeScientificNameTranslate(subtree);
		}else{
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
		}
		paltreeService.setTree(subtree);
		paltreeService.graphicsGeneration(subtree,subTreeImageFielName);


	}

	public void subtreeExtraction(List<String> subTreeNamelist,
			String treFileName, String resOutTreFileName,  String subTreeImageFielName,String nameChoice)
			throws Exception {

		SimpleTree m_palTree;

		Tree ret_subtree;

		NewickFileIO nfio = new NewickFileIO();
		PalTreeServiceImpl paltreeService = new PalTreeServiceImpl();

		RootedTreeSubtreeUtils rtsu = new RootedTreeSubtreeUtils();
		m_palTree = (SimpleTree) nfio.inputNewickFile(treFileName);

		ret_subtree = rtsu.getSpecifiedSubTree(m_palTree, subTreeNamelist);
		mainTree = m_palTree;
		subtree = ret_subtree;
		if (nameChoice.equals("2")){
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
			treeScientificNameTranslate(mainTree);
			treeScientificNameTranslate(subtree);
		}else{
			nfio.outputNewickFile(ret_subtree, resOutTreFileName);
		}
		paltreeService.setTree(subtree);
		paltreeService.graphicsGeneration(subtree,subTreeImageFielName);


	}
	
	public void treeScientificNameTranslate(Tree tree) throws ServiceException, DaoException{
	//	HashMap nameMap = (HashMap)speciesNameDao.getAllSpeciesNames();
		HashMap nameMap = (HashMap)mongoSpeciesNameDao.getAllSpeciesNameMap();
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
}

