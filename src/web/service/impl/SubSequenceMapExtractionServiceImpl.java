package web.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pal.tree.SimpleTree;
import pal.tree.Tree;
import pal.tree.TreeParseException;
import phyloGeneticAnalysis.Exception.nameFormatException;
import phyloGeneticAnalysis.Util.GeneSequenceUtil;
import phyloGeneticAnalysis.Util.RootedTreeSubtreeIOUtils;
import phyloGeneticAnalysis.Util.RootedTreeSubtreeUtils;
//import phyloGeneticAnalysis.Util.speciesNameFormatUtils;
import phyloGeneticAnalysis.io.CsvFileIO;
import phyloGeneticAnalysis.io.NewickFileIO;
import phyloGeneticAnalysis.io.PhySequenceFileIO;
import phyloGeneticAnalysis.io.SequenceIDFileIO;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.service.SubSequenceMapExtractionService;

public class SubSequenceMapExtractionServiceImpl implements SubSequenceMapExtractionService{
	public String subtreeExtraction(String subTreeNamelistFile,String allTaxaNameFile,
			String treFileName, String resOutTreFileName)
			throws Exception {

		List<String> subtreeNameList;
		CsvFileIO csvf = new CsvFileIO();
		subtreeNameList = csvf.readCSVcolumn(subTreeNamelistFile, 0, true);		
		
		List<String> allNameList;
		allNameList = csvf.readCSVcolumn(allTaxaNameFile, 0, false);	
		
		String missing = compareMissingNames( subtreeNameList, allNameList);
		
		RootedTreeSubtreeIOUtils rtsu = new RootedTreeSubtreeIOUtils();
		
		rtsu.inputNewickFileExtractSubtreeAndOuputNewickFile(treFileName, resOutTreFileName, subtreeNameList);		
		return missing;
	}
	
	public String subSequenceFileExtraction(String subTreeNamelistFile,String allTaxaNameFile,
			String seqIDFileName, String phySeqFileName,
			String resSeqIDOutFileName, String resPhySeqOutFileName) throws Exception {
		
		CsvFileIO csvf = new CsvFileIO();
		List<String> subtreeNameList;
		subtreeNameList = csvf.readCSVcolumn(subTreeNamelistFile, 0, true);
		
		List<String> allNameList;
		allNameList = csvf.readCSVcolumn(allTaxaNameFile, 0, false);
		
		String missing = compareMissingNames( subtreeNameList, allNameList);
		
		subSequenceIDMapFileExtraction(subtreeNameList, seqIDFileName,
				resSeqIDOutFileName);
		subPhySequenceMapFileExtraction(subtreeNameList, phySeqFileName,
				resPhySeqOutFileName);
		
		return missing;

	}
	
	public void subSequenceIDMapFileExtraction(List subtreeNameList,
			String inFileName, String resOutFileName)
			throws Exception {

		SequenceIDFileIO seqIDio = new SequenceIDFileIO();		
		List speToSequenceIDList = (List) seqIDio.readSequenceIDFile(inFileName);
		GeneSequenceUtil gsutil = new GeneSequenceUtil();
		List subSequenceIDlistList = gsutil.getSpecifiedSpeiceNameToGeneIDMap(speToSequenceIDList, subtreeNameList);
		seqIDio.saveSequenceIDFile(resOutFileName, subSequenceIDlistList);
	}
	
	public void subPhySequenceMapFileExtraction(List subtreeNameList,
			String inFileName, String resOutFileName)
			throws Exception {

		PhySequenceFileIO physeqio = new PhySequenceFileIO();		
		physeqio.readExtractAndSaveToPhyFile(inFileName,resOutFileName,subtreeNameList);
	}
	
	public String compareMissingNames(List<String> subtreeNameList,List<String> allNameList){
		String missing = "";
		for (int i = 0; i < subtreeNameList.size(); i++) {
			String subname = subtreeNameList.get(i);
			if (!allNameList.contains(subname))
				missing += subname + " @ ";
		}
		return missing;	
	}
}
