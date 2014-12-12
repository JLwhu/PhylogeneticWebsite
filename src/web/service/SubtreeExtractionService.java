/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 11, 2013
 */
package web.service;

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
import web.dao.SpeciesNameDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.MongoSpeciesName;
import web.service.impl.PalTreeServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Interface SubtreeExtractionService.
 */
public interface SubtreeExtractionService {
	
	/**
	 * Subtree extraction.
	 *
	 * @param subTreeNamelistFile the sub tree namelist file
	 * @param treFileName the tre file name
	 * @param resOutTreFileName the res out tre file name
	 * @throws ServiceException the service exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TreeParseException the tree parse exception
	 * @throws nameFormatException the name format exception
	 */


	public void subtreeExtraction(String subTreeNamelistFile,
			String treFileName, String nameMappingFileName,
			String resOutTreFileName, String mainTreeImageFileName,
			String subTreeImageFielName, String nameChoice, String inputChoice) throws Exception;
	
	public void subtreeExtractionId(List<Integer> subTreeSpeciesIdlist,
			String treFileName, String nameMappingFileName,
			String resOutTreFileName, String subTreeImageFielName,
			String nameChoice) throws Exception ;

	public void subtreeExtraction(List<String> subTreeNamelist,
			String treFileName,String nameMappingFileName, String resOutTreFileName,  String subTreeImageFielName,String nameChoice)
			throws Exception;
	
	public void treeScientificNameTranslate(Tree tree, HashMap nameMap) throws Exception;
	public double getMainTreeDiversity() throws Exception;
	public double getSubTreeDiversity() throws Exception;
	
	public HashMap getAllSpeciesSciToCommonNameMap(String mappingFileName) throws Exception;
}
