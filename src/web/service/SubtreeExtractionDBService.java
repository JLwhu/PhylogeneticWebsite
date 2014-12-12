/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 11, 2013
 */
package web.service;

import java.io.IOException;
import java.util.List;

import pal.tree.TreeParseException;
import phyloGeneticAnalysis.Exception.nameFormatException;
import web.dao.SpeciesNameDao;
import web.exception.DaoException;
import web.exception.ServiceException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SubtreeExtractionService.
 */
public interface SubtreeExtractionDBService {
	
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
	
	public SpeciesNameDao getSpeciesNameDao();
	public void setSpeciesNameDao(SpeciesNameDao speciesNameDao);
	public void subtreeExtraction(String subTreeNamelistFile,
			String treFileName, String resOutTreFileName, String mainTreeImageFileName, String subTreeImageFielName,String nameChoice)
			throws Exception;
	public void subtreeExtraction(List<String> subTreeNamelist,
			String treFileName, String resOutTreFileName,  String subTreeImageFielName,String nameChoice)
			throws Exception;
	public void subtreeExtractionId(List<Integer> subTreeSpeciesIdlist,
			String treFileName, String resOutTreFileName,  String subTreeImageFielName,String nameChoice)
			throws Exception;
	
	public double getMainTreeDiversity() throws Exception;
	public double getSubTreeDiversity() throws Exception;
}
