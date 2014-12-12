package web.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
import com.opensymphony.xwork2.ActionSupport;
 
public class MultipleFileUploadAction extends ActionSupport{
 
	private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadContentType = new ArrayList<String>();
	private List<String> fileUploadFileName = new ArrayList<String>();
 
	public List<File> getFileUpload() {
		return fileUpload;
	}
 
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}
 
	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}
 
	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
 
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
 
	public String upload() throws Exception{
 
		for (File file: fileUpload) {
	        System.out.println("File :" + file);
	    }
 
	    for (String fileName: fileUploadFileName) {
	        System.out.println("Filename : " + fileName);
	    }
 
	    for (String fileContentType: fileUploadContentType) {
	        System.out.println("File type : " + fileContentType);
	    }
	    return SUCCESS;
 
	}
 
	public String display() {
		return NONE;
	}
 
}