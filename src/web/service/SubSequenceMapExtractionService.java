package web.service;

import java.io.IOException;
import java.util.List;

import pal.tree.TreeParseException;
import phyloGeneticAnalysis.Exception.nameFormatException;
import phyloGeneticAnalysis.Util.GeneSequenceUtil;
import phyloGeneticAnalysis.Util.RootedTreeSubtreeIOUtils;
import phyloGeneticAnalysis.io.CsvFileIO;
import phyloGeneticAnalysis.io.PhySequenceFileIO;
import phyloGeneticAnalysis.io.SequenceIDFileIO;
import web.exception.DaoException;
import web.exception.ServiceException;

public interface SubSequenceMapExtractionService {
	public String subtreeExtraction(String subTreeNamelistFile,String allTaxaNameFile,
			String treFileName, String resOutTreFileName)
			throws Exception ;
	
	public String subSequenceFileExtraction(String subTreeNamelistFile,String allTaxaNameFile,
			String seqIDFileName, String phySeqFileName,
			String resSeqIDOutFileName, String resPhySeqOutFileName) throws Exception;
	
	public void subSequenceIDMapFileExtraction(List subtreeNameList,
			String inFileName, String resOutFileName)
			throws Exception ;
	public void subPhySequenceMapFileExtraction(List subtreeNameList,
			String inFileName, String resOutFileName)
			throws Exception;
	public String compareMissingNames(List<String> subtreeNameList,List<String> allNameList);
}
