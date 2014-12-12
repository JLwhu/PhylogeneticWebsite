package web.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import web.service.SubSequenceMapExtractionService;

public class SubSequenceMapAction extends UploadFileAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -197101104812248494L;

	/** The log. */
	private Log log = LogFactory.getLog(SubSequenceMapActionOld.class);
	
	/** The subtree extraction service. */
	private SubSequenceMapExtractionService subSequenceMapExtractionService;

	/** The out file name. */
	private String outTreFileName;
	
	private String choice;
	
	private String missingTaxa;
	
	public SubSequenceMapExtractionService getSubSequenceMapExtractionService() {
		return subSequenceMapExtractionService;
	}

	public void setSubSequenceMapExtractionService(
			SubSequenceMapExtractionService subSequenceMapExtractionService) {
		this.subSequenceMapExtractionService = subSequenceMapExtractionService;
	}

	public String getOutTreFileName() {
		return outTreFileName;
	}

	public void setOutTreFileName(String outTreFileName) {
		this.outTreFileName = outTreFileName;
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
	 * @throws IOException 
     * @throws Exception the exception
     */
    public String subSequenceMapExtraction() throws IOException {
		log.debug("subSequenceMapExtraction");
		
		String subTreeName;
		String treFileName;
		String seqIDFileName;
		String phySeqFileName;
		String resOutTreFileName;
		String resOutPhyFileName;
		
		ServletContext context = ServletActionContext.getServletContext();
		HttpServletResponse response  = ServletActionContext.getResponse();
		
		if (upload()) {
			
			String path = getFileSavePath().replaceAll("\\\\", "/") + "/";
			subTreeName = path + getUploadFileFileName();
			
			treFileName = (String)context.getAttribute("treeFile");
    		treFileName = ServletActionContext.getServletContext().getRealPath(treFileName);
    		
    		seqIDFileName = (String)context.getAttribute("seqIDFile");
    		seqIDFileName = ServletActionContext.getServletContext().getRealPath(seqIDFileName);
    		
    		phySeqFileName= (String)context.getAttribute("phySeqFile");
    		phySeqFileName = ServletActionContext.getServletContext().getRealPath(phySeqFileName);
    		
    		String allTaxaNamFile =  (String)context.getAttribute("allTaxaNameFile");
    		allTaxaNamFile = ServletActionContext.getServletContext().getRealPath(allTaxaNamFile);
 			
    		try {
    			
    			if (choice.equals("2")) {
    				outTreFileName = randomizeFilename(path, outTreFileName);
    				setOutTreFileName(outTreFileName);
    				resOutTreFileName = path + outTreFileName;
    				missingTaxa = subSequenceMapExtractionService.subtreeExtraction(subTreeName,allTaxaNamFile, treFileName, resOutTreFileName);
    				setMissingTaxa(missingTaxa);
    			} else {
    				outTreFileName = "subSeq.phy";
    				outTreFileName = randomizeFilename(path, outTreFileName);
    				setOutTreFileName(outTreFileName);
    				resOutPhyFileName = path + outTreFileName;
    				String resSeqIDOutFileName = outTreFileName.substring(0, outTreFileName.lastIndexOf(".")) + "ID" + ".txt";
    				resSeqIDOutFileName = path + resSeqIDOutFileName;
    				missingTaxa = subSequenceMapExtractionService.subSequenceFileExtraction(
    						subTreeName, allTaxaNamFile, seqIDFileName, phySeqFileName,
    						resSeqIDOutFileName, resOutPhyFileName);
    				setMissingTaxa(missingTaxa);
    			}
    			
    		} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
    		
			return SUCCESS;
			
//			File file = new File(subTreeName);
//			 
//    		if(file.delete()){
//    			System.out.println(file.getName() + " is deleted!");
//    		}else{
//    			System.out.println("Delete operation is failed.");
//    		}

			
		} else {
			response.sendError(2323);
			return ERROR;
		}
    }
}
