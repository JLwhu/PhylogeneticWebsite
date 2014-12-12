package web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import web.util.ApplicationListener;

import com.opensymphony.xwork2.ActionSupport;

public class UploadFileAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5290278871207017968L;

	private File uploadFile;
    
    private String uploadFileFileName;
    
    private String uploadFileContentType;
    
    private String fileSavePath;

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getFileSavePath() {
		return ServletActionContext.getServletContext().getRealPath(fileSavePath);
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}
    
	public String randomizeFilename(String path, String filename) {
		Random random = new Random();
        String resfilename = filename.substring(0,filename.lastIndexOf(".")) + random.nextLong()
                + filename.substring(filename.lastIndexOf("."));
        while (new File(path + resfilename).exists()) {
        	resfilename = filename.substring(0,filename.lastIndexOf(".")) + random.nextLong()
                    + filename.substring(filename.lastIndexOf("."));
        }
        return resfilename;    
    }
	
	public boolean IPLimiter() throws IOException {
		
		boolean defaultTag = true;
		
		ServletContext context = ServletActionContext.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response  = ServletActionContext.getResponse();
		
		String ip = request.getRemoteAddr();
		boolean isContainsIP = ApplicationListener.IPMap.containsKey(ip);
		
		int serverLimmitPerDay = Integer.parseInt((String) context.getAttribute("ServerLimitPerDay"));
		int IPLimmitInPeriod = Integer.parseInt((String) context.getAttribute("IPLimitInPeriod"));
		int IPLimmitPerDay = Integer.parseInt((String) context.getAttribute("IPLimitPerDay"));
		int IPLimmitPeriod = Integer.parseInt((String) context.getAttribute("IPLimitPeriod"));
		
		//System.out.println(serverLimmitPerDay+" "+IPLimmitInPeriod+""+IPLimmitPerDay+" "+IPLimmitPeriod);
		
		long baseTime;
		long currentTime;
		int baseCounts;
		int totalCounts;
		
		if (isContainsIP) {
			currentTime = new Date().getTime();
			totalCounts = Integer.parseInt(ApplicationListener.IPMap.get(ip).split("@")[0]);
			baseCounts = Integer.parseInt(ApplicationListener.IPMap.get(ip).split("@")[1]);
			baseTime = Long.parseLong(ApplicationListener.IPMap.get(ip).split("@")[2]);
			
			if (totalCounts < IPLimmitPerDay) {
				if (currentTime - baseTime > IPLimmitPeriod*60*1000) {
					baseCounts = ++totalCounts;
					baseTime = currentTime - (currentTime - baseTime) % IPLimmitPeriod*60*1000;
					ApplicationListener.IPMap.put(ip, totalCounts + "@" + baseCounts + "@" + baseTime);
				} else if (totalCounts + 1 - baseCounts < IPLimmitInPeriod) {
					ApplicationListener.IPMap.put(ip, ++totalCounts + "@" + baseCounts + "@" + baseTime);
				} else {
					response.sendError(2020);
					defaultTag = false;
				}
			} else {
				response.sendError(2121);
				defaultTag = false;
			}
		} else if (ApplicationListener.IPMap.size() < serverLimmitPerDay){
			totalCounts = 1;
			baseCounts = 1;
			baseTime = new Date().getTime();
			ApplicationListener.IPMap.put(ip, totalCounts + "@" + baseCounts + "@" + baseTime);
		} else {
			response.sendError(2222);
			defaultTag = false;
		}

		return defaultTag;
	}
	
	public boolean upload() throws IOException {
		
		boolean defaultTag = true;
		
		if (uploadFile != null) {
			String path = getFileSavePath().replaceAll("\\\\", "/") + "/";
			String subTreeName = randomizeFilename(path, uploadFileFileName);
			setUploadFileFileName(subTreeName);
			subTreeName = path + subTreeName;
			
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			try {
				FileInputStream fis = new FileInputStream(uploadFile);
				FileOutputStream fos = new FileOutputStream(new File(subTreeName));
				byte [] buf = new byte[4096];
				int len = -1;
				while ((len = fis.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				defaultTag = false;
			}
		} else {
			defaultTag = false;
		}
		
		return defaultTag;
	}
    
    
}
