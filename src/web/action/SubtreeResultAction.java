/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 11, 2013
 */
package web.action;

import com.opensymphony.xwork2.ActionSupport;

public class SubtreeResultAction   extends ActionSupport{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The out file name. */
	private String outFileName;
	private String outputPath;
	
	/** The sub tree diversity. */
	private double subTreeDiversity;
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
	

	public double getSubTreeDiversity() {
		return subTreeDiversity;
	}

	
	public void setSubTreeImageFileName(String subTreeImageFileName) {
		this.subTreeImageFileName = subTreeImageFileName;
	}


	public String getSubTreeImageFileName() {
		return subTreeImageFileName;
	}
	
    public String execute() throws Exception {

        return SUCCESS;
    }

}

