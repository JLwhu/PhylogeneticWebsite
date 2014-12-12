package web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadFilesAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<File> filesUpload = new ArrayList<File>();// 
	private List<String> filesUploadContentType = new ArrayList<String>();
	private List<String> filesUploadFileName = new ArrayList<String>(); 

	
	private String fileSavePath;// 
								// 

	public String getFileSavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fileSavePath);
	}

	public void setFileSavePath(String fileSavePath) {
		System.out.print(fileSavePath);
		this.fileSavePath = fileSavePath;
	}

	public List<File> getFilesUpload() {
		return filesUpload;
	}

	public void setFileUpload(List<File> filesUpload) {
		System.out.print("abc");
		this.filesUpload = filesUpload;
	}

	public List<String> getfilesUploadContentType() {
		return filesUploadContentType;
	}

	public void setFileUploadContentType(List<String> filesUploadFileName) {
		this.filesUploadFileName = filesUploadFileName;
	}

	public List<String> getFileUploadFileName() {
		return filesUploadFileName;
	}

	public void setFileUploadFileName(List<String> filesUploadFileName) {
		for (int j = 0; j < filesUploadFileName.size(); j++) {
		System.out.print(filesUploadFileName.get(j));
		}
		this.filesUploadFileName = filesUploadFileName;
	}

	public String upload() throws Exception {
		if (filesUpload != null) {

			for (int j = 0; j < filesUpload.size(); j++) {
				System.out.println("======");
				System.out.println("===" + this.filesUploadContentType.get(j));
				FileInputStream fis = new FileInputStream(getFilesUpload().get(
						j));
				File file = new File(getFileSavePath().replaceAll(
						"[url=]\\\\[/url]", "/"));
				if (!file.exists()) {
					file.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(getFileSavePath()
						.replaceAll("[url=]\\\\[/url]", "/")
						+ "/"
						+ getFileUploadFileName());
				int i = 0;
				byte[] buf = new byte[1024];
				while ((i = fis.read(buf)) != -1) {
					fos.write(buf, 0, i);
				}
				fis.close();
				fos.close();
			}
			
		}
		return "success";
	}
}
