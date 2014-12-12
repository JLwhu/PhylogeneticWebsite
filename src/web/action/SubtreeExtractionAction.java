package web.action;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import web.service.PalTreeService;
import web.service.SubtreeExtractionService;


public class SubtreeExtractionAction extends UploadFileAction {

	private static final long serialVersionUID = -8690924260069467860L;
	
	/** The log. */
	private Log log = LogFactory.getLog(SubtreeExtractionActionOld.class);

	/** The subtree extraction service. */
	private SubtreeExtractionService subtreeExtractionService;
	private PalTreeService palTreeService;

	private String outTreFileName;
	private String mainTreeImageFileName;

	private String subTreeImageFileName;
	
	private String nameChoice;
	private String inputChoice;
	
	/** The main tree diversity. */
	private double mainTreeDiversity;

	/** The sub tree diversity. */
	private double subTreeDiversity;
	
	public SubtreeExtractionService getSubtreeExtractionService() {
		return subtreeExtractionService;
	}

	public void setSubtreeExtractionService(
			SubtreeExtractionService subtreeExtractionService) {
		this.subtreeExtractionService = subtreeExtractionService;
	}

	public PalTreeService getPalTreeService() {
		return palTreeService;
	}

	public void setPalTreeService(PalTreeService palTreeService) {
		this.palTreeService = palTreeService;
	}
	
	public String getOutTreFileName() {
		return outTreFileName;
	}

	public void setOutTreFileName(String outTreFileName) {
		this.outTreFileName = outTreFileName;
	}

	public String getMainTreeImageFileName() {
		return mainTreeImageFileName;
	}

	public void setMainTreeImageFileName(String mainTreeImageFileName) {
		this.mainTreeImageFileName = mainTreeImageFileName;
	}

	public String getSubTreeImageFileName() {
		return subTreeImageFileName;
	}

	public void setSubTreeImageFileName(String subTreeImageFileName) {
		this.subTreeImageFileName = subTreeImageFileName;
	}

	public String getNameChoice() {
		return nameChoice;
	}

	public void setNameChoice(String nameChoice) {
		this.nameChoice = nameChoice;
	}

	public String getInputChoice() {
		return inputChoice;
	}

	public void setInputChoice(String inputChoice) {
		this.inputChoice = inputChoice;
	}

	public double getMainTreeDiversity() {
		return mainTreeDiversity;
	}

	public void setMainTreeDiversity(double mainTreeDiversity) {
		this.mainTreeDiversity = mainTreeDiversity;
	}

	public double getSubTreeDiversity() {
		return subTreeDiversity;
	}

	public void setSubTreeDiversity(double subTreeDiversity) {
		this.subTreeDiversity = subTreeDiversity;
	}
	
	public String subtreeExtraction() throws IOException {
		
		log.debug("subtreeExtraction");
		
		String subTreeName;
		String treFileName;
		String resOutTreFileName;
		String resMainImage;
		String resSubImage;
		
		if (!IPLimiter()) {
			return ERROR;
		}

		ServletContext context = ServletActionContext.getServletContext();
		HttpServletResponse response  = ServletActionContext.getResponse();
		
		if (upload()) {
			String path = getFileSavePath().replaceAll("\\\\", "/") + "/";
			subTreeName = path + getUploadFileFileName();
			
			treFileName =  (String) context.getAttribute("treFilePhyloAnalysis");
			treFileName = ServletActionContext.getServletContext().getRealPath(treFileName);
			
			outTreFileName = randomizeFilename(path, outTreFileName);
			setOutTreFileName(outTreFileName);
			resOutTreFileName = path + outTreFileName;
			
			mainTreeImageFileName = randomizeFilename(path,	mainTreeImageFileName);
			setMainTreeImageFileName(mainTreeImageFileName);
			resMainImage = path + mainTreeImageFileName;

			subTreeImageFileName = randomizeFilename(path, subTreeImageFileName);
			setSubTreeImageFileName(subTreeImageFileName);
			resSubImage = path + subTreeImageFileName;
			
			String mappingFileName = (String) context.getAttribute("sciCommonNameMappingFile");
			mappingFileName = ServletActionContext.getServletContext().getRealPath(mappingFileName);
			
			try {
				subtreeExtractionService.subtreeExtraction(subTreeName,
						treFileName, mappingFileName, resOutTreFileName,
						resMainImage, resSubImage, nameChoice, inputChoice);
				
				mainTreeDiversity = subtreeExtractionService.getMainTreeDiversity();
				subTreeDiversity = subtreeExtractionService.getSubTreeDiversity();
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(2424);
				return ERROR;
			}
			
			return SUCCESS;
			
		} else {
			response.sendError(2323);
			return ERROR;
		}
	}
			
//			File file = new File(subTreeName);
//			 
//    		if(file.delete()){
//    			System.out.println(file.getName() + " is deleted!");
//    		} else {
//    			System.out.println("Delete operation is failed.");
//    		}

}
