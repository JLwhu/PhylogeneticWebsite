package web.action;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class FileDeleteAction  implements Action {

		private String fileName;

		// 指定要被下载的文件路径，struts.xml中配置

		private String inputPath;

		public void setInputPath(String value) {

			inputPath = value;

		}

		public void setFileName(String fileName) {

			this.fileName = fileName;

		}

		public String getFileName() {

			return fileName;

		}

		public InputStream getInputStream() throws Exception {

			String url = inputPath + "/" + fileName;

			int size = url.length() - 1;

			for (int i = 0; i < size; i++)

				url = url.replace("//", "/");

			//System.out.print(url);
			return ServletActionContext.getServletContext()

			.getResourceAsStream(url);

		}

		public String execute() throws Exception {

			/*
			 * 
			 * 获取目标文件在服务器中保存的目录。若不在这个目录则拒绝下载，否则，
			 * 
			 * 稍微精通struts2的人可能试图获取WEB-INF下的文件，那就不安全了。
			 */

			String downloadDir = ServletActionContext.getServletContext()

			.getRealPath("/upload");

			// 获取目标文件的绝对路径

			String downloadFile = ServletActionContext.getServletContext()

			.getRealPath(inputPath);

			/*
			 * 
			 * 防止企图下载不在目录downloadDir下的文件，以保障安全。若不在这个目
			 * 
			 * 录则拒绝下载，否则，稍微精通struts2的人可能试图获取WEB-INF下 的文件，那就不安全了。
			 */

			if (!downloadFile.startsWith(downloadDir)) {

				return null;

			}
			
			File file = new File(downloadFile+ "/" + fileName);
			System.out.println(file.getName());
			 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println(file.getName() + " Delete operation is failed.");
    		}

			return SUCCESS;

		}
}
