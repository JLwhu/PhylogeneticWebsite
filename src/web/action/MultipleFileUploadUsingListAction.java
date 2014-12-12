/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 10, 2013
 */
package web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * Showcase action - multiple file upload using List.
 *
 * @version $Date$ $Id$
 */
public class MultipleFileUploadUsingListAction extends ActionSupport {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The uploads. */
	private List<File> uploads = new ArrayList<File>();
    
    /** The upload file names. */
    private List<String> uploadFileNames = new ArrayList<String>();
    
    /** The upload content types. */
    private List<String> uploadContentTypes = new ArrayList<String>();
	
	/** The file save path. */
	private String fileSavePath;


    /**
     * Gets the upload.
     *
     * @return the upload
     */
    public List<File> getUpload() {
        return this.uploads;
    }
    
    /**
     * Sets the upload.
     *
     * @param uploads the new upload
     */
    public void setUpload(List<File> uploads) {
        this.uploads = uploads;
    }

    /**
     * Gets the upload file name.
     *
     * @return the upload file name
     */
    public List<String> getUploadFileName() {
        return this.uploadFileNames;
    }
    
    /**
     * Sets the upload file name.
     *
     * @param uploadFileNames the new upload file name
     */
    public void setUploadFileName(List<String> uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    /**
     * Gets the upload content type.
     *
     * @return the upload content type
     */
    public List<String> getUploadContentType() {
        return this.uploadContentTypes;
    }
    
    /**
     * Sets the upload content type.
     *
     * @param contentTypes the new upload content type
     */
    public void setUploadContentType(List<String> contentTypes) {
        this.uploadContentTypes = contentTypes;
    }
    
	/**
	 * Gets the file save path.
	 *
	 * @return the file save path
	 */
	public String getFileSavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fileSavePath);
	}
	
	/**
	 * Sets the file save path.
	 *
	 * @param fileSavePath the new file save path
	 */
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}


    /**
     * Upload.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String upload() throws Exception {
    	
    	
    	if (uploads != null) {
    		
    		List<String> upfileNames = new ArrayList<String>();

			for (int j = 0; j < uploads.size(); j++) {
			//	System.out.println("======");
			//	System.out.println("===" + this.uploadContentTypes.get(j));
				FileInputStream fis = new FileInputStream(uploads.get(
						j));
				File file = new File(getFileSavePath().replaceAll("[url=]\\\\[/url]", "/"));
				if (!file.exists()) {
					file.mkdirs();
				}
				

				String path = getFileSavePath()
						.replaceAll("[url=]\\\\[/url]", "/")
						+ "/";
                String filename = uploadFileNames.get(j);                         
                filename = randomizeFilename(path,filename);

                upfileNames.add(filename);
				
		/*		FileOutputStream fos = new FileOutputStream(getFileSavePath()
						.replaceAll("[url=]\\\\[/url]", "/")
						+ "/"
						+ uploadFileNames.get(j)); */
				FileOutputStream fos = new FileOutputStream(path+filename);
				int i = 0;
				byte[] buf = new byte[1024];
				while ((i = fis.read(buf)) != -1) {
					fos.write(buf, 0, i);
				}
				fis.close();
				fos.close();
			}
			setUploadFileName(upfileNames);
			
		}
     //   System.out.println("\n\n");
        return SUCCESS;
    }
    
    public String randomizeFilename(String path, String filename) throws Exception {
		Random random = new Random();
        String resfilename = filename.substring(0,filename.lastIndexOf(".")) + random.nextLong()
                + filename.substring(filename.lastIndexOf("."));
        while (new File(path+resfilename).exists()) {
        	resfilename = filename.substring(0,filename.lastIndexOf(".")) + random.nextLong()
                    + filename.substring(filename.lastIndexOf("."));
        }
        return resfilename;    
    }
    
}