/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 11, 2013
 */
package web.action;

import com.opensymphony.xwork2.ActionSupport;

public class SubtreeExtractionResultAction   extends ActionSupport{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The out file name. */
	private String outFileName;
	private String outputPath;
	
	/** The main tree diversity. */
	private double mainTreeDiversity;
	
	/** The sub tree diversity. */
	private double subTreeDiversity;
	private String mainTreeImageFileName; 
	private String subTreeImageFileName; 
	
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
	
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getOutputPath() {
		return outputPath;
	}
	
	/**
	 * Gets the main tree diversity.
	 *
	 * @return the main tree diversity
	 */
	public double getMainTreeDiversity() {
		return mainTreeDiversity;
	}
	
	public double getSubTreeDiversity() {
		return subTreeDiversity;
	}

    

	public void setMaintreeImageFileName(String mainTreeImageFileName) {
		this.mainTreeImageFileName = mainTreeImageFileName;
	}


	public String getMaintreeImageFileName() {
		return mainTreeImageFileName;
	}
	
	public void setSubTreeImageFileName(String subTreeImageFileName) {
		this.subTreeImageFileName = subTreeImageFileName;
	}


	public String getSubTreeImageFileName() {
		return subTreeImageFileName;
	}
	
    public String subtreeExtraction() throws Exception {

        return SUCCESS;
    }

}
