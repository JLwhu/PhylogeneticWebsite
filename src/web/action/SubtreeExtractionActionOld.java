/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 11, 2013
 */
package web.action;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import web.service.PalTreeService;
import web.service.SubtreeExtractionService;

// TODO: Auto-generated Javadoc
/**
 * The Class subtreeExtractionAction.
 */
public class SubtreeExtractionActionOld extends MultipleFileUploadUsingListAction {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The json. */
	private Map<String, String> json;

	/** The log. */
	private Log log = LogFactory.getLog(SubtreeExtractionActionOld.class);

	/** The subtree extraction service. */
	private SubtreeExtractionService subtreeExtractionService;
	private PalTreeService palTreeService;

	/** The out file name. */
	private String outFileName;

	/** The main tree diversity. */
	private double mainTreeDiversity = 0;

	/** The sub tree diversity. */
	private double subTreeDiversity = 0;

	private String mainTreeImageFileName;
	private String subTreeImageFileName;
	
	private String nameChoice;
	private String inputChoice;

	/**
	 * Sets the out file name.
	 * 
	 * @param outFileName
	 *            the new out file name
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

	public void setNameChoice(String nameChoice) {
		this.nameChoice = nameChoice;
	}

	public String getNameChoice() {
		return nameChoice;
	}

	public void setInputChoice(String inputChoice) {
		this.inputChoice = inputChoice;
	}

	public String getInputChoice() {
		return inputChoice;
	}

	public void setSubTreeImageFileName(String subTreeImageFileName) {
		this.subTreeImageFileName = subTreeImageFileName;
	}

	public String getSubTreeImageFileName() {
		return subTreeImageFileName;
	}

	public void setMainTreeImageFileName(String mainTreeImageFileName) {
		this.mainTreeImageFileName = mainTreeImageFileName;
	}

	public String getMainTreeImageFileName() {
		return mainTreeImageFileName;
	}

	/**
	 * Gets the main tree diversity.
	 * 
	 * @return the main tree diversity
	 */
	public double getMainTreeDiversity() {
		return mainTreeDiversity;
	}

	/**
	 * Sets the main tree diversity.
	 * 
	 * @param mainTreeDiversity
	 *            the new main tree diversity
	 */
	public void setMainTreeDiversity(double mainTreeDiversity) {
		this.mainTreeDiversity = mainTreeDiversity;
	}

	/**
	 * Gets the sub tree diversity.
	 * 
	 * @return the sub tree diversity
	 */
	public double getSubTreeDiversity() {
		return subTreeDiversity;
	}

	/**
	 * Sets the sub tree diversity.
	 * 
	 * @param subTreeDiversity
	 *            the new sub tree diversity
	 */
	public void setSubTreeDiversity(double subTreeDiversity) {
		this.subTreeDiversity = subTreeDiversity;
	}

	/**
	 * Gets the subtree extraction service.
	 * 
	 * @return the subtree extraction service
	 */
	public SubtreeExtractionService getsubtreeExtractionService() {
		return this.subtreeExtractionService;
	}

	/**
	 * Sets the subtree extraction service.
	 * 
	 * @param subtreeExtractionService
	 *            the new subtree extraction service
	 */
	public void setsubtreeExtractionService(
			SubtreeExtractionService subtreeExtractionService) {
		this.subtreeExtractionService = subtreeExtractionService;
	}

	public PalTreeService getPalTreeService() {
		return this.palTreeService;
	}

	public void setPalTreeService(PalTreeService palTreeService) {
		this.palTreeService = palTreeService;
	}

	/**
	 * Upload.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String subtreeExtraction() throws Exception {
		log.debug("subtreeExtraction");
		String subTreeNamelistFile;
		String treFileName;
		String resOutTreFileName;
		String resMainImage;
		String resSubImage;
		super.upload();

		ServletContext context = ServletActionContext.getServletContext();

		if (getUpload() != null) {
			String path = getFileSavePath()
			// .replaceAll("[url=]\\\\[/url]", "/")
					+ "/";
			subTreeNamelistFile = path + getUploadFileName().get(0);
	//		treFileName = path + getUploadFileName().get(1);
			treFileName =  (String) context
					.getAttribute("treFilePhyloAnalysis");
			treFileName = ServletActionContext.getServletContext()
					.getRealPath(treFileName);

			outFileName = randomizeFilename(path, outFileName);
			setOutFileName(outFileName);
			resOutTreFileName = path + outFileName;

			mainTreeImageFileName = randomizeFilename(path,
					mainTreeImageFileName);
			setMainTreeImageFileName(mainTreeImageFileName);
			resMainImage = path + mainTreeImageFileName;

			subTreeImageFileName = randomizeFilename(path, subTreeImageFileName);
			setSubTreeImageFileName(subTreeImageFileName);
			resSubImage = path + subTreeImageFileName;

			String mappingFileName = (String) context
					.getAttribute("sciCommonNameMappingFile");
			mappingFileName = ServletActionContext.getServletContext()
					.getRealPath(mappingFileName);

			subtreeExtractionService.subtreeExtraction(subTreeNamelistFile,
					treFileName, mappingFileName, resOutTreFileName,
					resMainImage, resSubImage, nameChoice, inputChoice);
			mainTreeDiversity = subtreeExtractionService.getMainTreeDiversity();
			subTreeDiversity = subtreeExtractionService.getSubTreeDiversity();
			
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
