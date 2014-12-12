package web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MultipleFileDeleteAction extends ActionSupport {
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
    
    /** The upload file names. */
    private List<String> deletesFileNames = new ArrayList<String>();
	
	private String inputPath;


    public List<String> getDeleteFileNames() {
        return this.deletesFileNames;
    }

    public void setDeletesFileNames(List<String> deletesFileNames) {
        this.deletesFileNames = deletesFileNames;
    }

	public String getInputPath() {
		return ServletActionContext.getServletContext().getRealPath(
				inputPath);
	}
	

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}


    /**
     * delete.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String execute() throws Exception {   	    	
    	if (deletesFileNames != null) {   		
			String downloadDir = ServletActionContext.getServletContext().getRealPath("/upload");
			String downloadFile = ServletActionContext.getServletContext().getRealPath(inputPath);
			if (!downloadFile.startsWith(downloadDir)) {
				return null;
			}
			
			String files = deletesFileNames.get(0);
			List<String> delFiles = new ArrayList<String>();
			if (files.length() > 2) {
				int idx = files.indexOf(",");
				int idx0 = 1;
				while (idx > 0) {
					delFiles.add(files.substring(idx0, idx));
					idx0 = idx + 2;
					idx = files.indexOf(",", idx + 1);
				}
				delFiles.add(files.substring(idx0, files.length()-1));
			}		
						
			for (int j = 0; j < delFiles.size(); j++) {								
				File file = new File(downloadFile+ "/" + delFiles.get(j));				 
	    		if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}	
			}
			
		}
        return SUCCESS;
    }

}
