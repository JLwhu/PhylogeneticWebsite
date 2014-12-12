package web.action;

import com.opensymphony.xwork2.ActionSupport;

public class SubSequenceMapExtractionResult extends ActionSupport{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The out file name. */
	private String fileName;
	private String choice;
	private String missingTaxa;
	
	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getChoice() {
		return choice;
	}



	public void setChoice(String choice) {
		this.choice = choice;
	}



	public String getMissingTaxa() {
		return missingTaxa;
	}



	public void setMissingTaxa(String missingTaxa) {
		this.missingTaxa = missingTaxa;
	}



	public String subSequenceMapExtraction() throws Exception {

        return SUCCESS;
    }
}
