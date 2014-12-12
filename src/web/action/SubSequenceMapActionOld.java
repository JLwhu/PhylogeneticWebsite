package web.action;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import web.service.SubSequenceMapExtractionService;

public class SubSequenceMapActionOld  extends MultipleFileUploadUsingListAction{
	/** The log. */
	private Log log = LogFactory.getLog(SubSequenceMapActionOld.class);
	
	/** The subtree extraction service. */
	private SubSequenceMapExtractionService subSequenceMapExtractionService;

	/** The out file name. */
	private String outFileName;
	
	private String choice;
	
	private String missingTaxa;
	

    public SubSequenceMapExtractionService getsubSequenceMapExtractionService() {
        return this.subSequenceMapExtractionService;
    }

    public void setsubSequenceMapExtractionService(SubSequenceMapExtractionService subSequenceMapExtractionService) {
        this.subSequenceMapExtractionService = subSequenceMapExtractionService;
    }
	
	/**
	 * Sets the out file name.
	 *
	 * @param outFileName the new out file name
	 */
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}

	/**
	 * Gets the out file name.
	 *
	 * @return the out file name
	 */
	public String getOutFileName() {
		return outFileName;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getChoice() {
		return choice;
	}
	
	
	public void setMissingTaxa(String missingTaxa) {
		this.missingTaxa = missingTaxa;
	}

	public String getMissingTaxa() {
		return missingTaxa;
	}
	
	/**
     * Upload.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String subSequenceMapExtraction() throws Exception {
		log.debug("subSequenceMapExtraction");
		String subTreeNamelistFile;
		String treFileName;
		String seqIDFileName;
		String phySeqFileName;
		String resOutTreFileName;
		String resOutPhyFileName;
		
		ServletContext context = ServletActionContext.getServletContext();
		
		super.upload();
     	if (getUpload() != null) {
    		String path = getFileSavePath()
					//.replaceAll("[url=]\\\\[/url]", "/")
					+ "/";
 			subTreeNamelistFile = path + getUploadFileName().get(0);
 			

    		treFileName = (String)context.getAttribute("treeFile");
    		treFileName = ServletActionContext.getServletContext().getRealPath(
    				treFileName);
    		
    		seqIDFileName = (String)context.getAttribute("seqIDFile");
    		seqIDFileName = ServletActionContext.getServletContext().getRealPath(
    				seqIDFileName);
    		
    		phySeqFileName= (String)context.getAttribute("phySeqFile");
    		phySeqFileName = ServletActionContext.getServletContext().getRealPath(
    				phySeqFileName);
    		
    		String allTaxaNamFile =  (String)context.getAttribute("allTaxaNameFile");
    		allTaxaNamFile = ServletActionContext.getServletContext().getRealPath(
    				allTaxaNamFile);
 			
			if (choice.equals("2")) {
				outFileName = randomizeFilename(path, outFileName);
				setOutFileName(outFileName);
				resOutTreFileName = path+ outFileName;
			//	treFileName = path + getUploadFileName().get(1);
				missingTaxa = subSequenceMapExtractionService.subtreeExtraction(
						subTreeNamelistFile,allTaxaNamFile, treFileName, resOutTreFileName);
			} else {
			//	seqIDFileName = path + getUploadFileName().get(1);
			//	phySeqFileName = path + getUploadFileName().get(2);
				outFileName = "subSeq.phy";
				outFileName = randomizeFilename(path, outFileName);
				setOutFileName(outFileName);
				resOutPhyFileName = path+ outFileName;
				String resSeqIDOutFileName = outFileName.substring(0,
						outFileName.lastIndexOf(".")) + "ID" + ".txt";
				resSeqIDOutFileName = path + resSeqIDOutFileName;
				missingTaxa = subSequenceMapExtractionService.subSequenceFileExtraction(
						subTreeNamelistFile,allTaxaNamFile, seqIDFileName, phySeqFileName,
						resSeqIDOutFileName, resOutPhyFileName);
			}		
			
			File file = new File(subTreeNamelistFile);
			 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

		}
        return SUCCESS;
    }
}
