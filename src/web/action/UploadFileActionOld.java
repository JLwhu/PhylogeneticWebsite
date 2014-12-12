package web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadFileActionOld extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File fileUpload;// 
	private String fileUploadContentType;// 
	private String fileUploadFileName;// 
	private String fileSavePath;// 
								// 

	public String getFileSavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				fileSavePath);
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		System.out.print(fileUploadFileName);
		this.fileUploadFileName = fileUploadFileName;
	}

	public String upload() throws Exception {
		System.out.println("======");
		System.out.println("===" + this.fileUploadContentType);
		FileInputStream fis = new FileInputStream(getFileUpload());

		String uploadDir = ServletActionContext.getServletContext().getRealPath("/upload");

		/*
		 * 
		 * 防止企图下载不在目录downloadDir下的文件，以保障安全。若不在这个目
		 * 
		 * 录则拒绝下载，否则，稍微精通struts2的人可能试图获取WEB-INF下 的文件，那就不安全了。
		 */

		if (!getFileSavePath().startsWith(uploadDir)) {
			return null;
		}
		
		
		File file = new File(getFileSavePath().replaceAll("[url=]\\\\[/url]",
				"/"));
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
		return "success";
	}
}
