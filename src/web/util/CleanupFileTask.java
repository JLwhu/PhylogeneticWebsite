package web.util;

import java.io.File;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class CleanupFileTask extends TimerTask{
	
	private static Log log = LogFactory.getLog(CleanupFileTask.class);
	
	private static boolean isRunning = false;
	 
	public void run() {
		if (!isRunning) {
			isRunning = true;
			log.debug("task begin...");
			
			//Cleanup IP list
			ApplicationListener.IPMap.clear();
			
			//Delete all temp files
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath((String) context.getAttribute("uploadPath"));
			File allFiles = new File(path);
			
			for (File file : allFiles.listFiles()) {
				if (file != null) {
					file.delete();
				}
			}
			
//			System.out.println("this is a test task");
//			System.out.println(ApplicationListener.IPMap);
			
			log.debug("task done...");
			isRunning = false;
		} else {
			log.debug("task unfinished...");
		}
	}

}
