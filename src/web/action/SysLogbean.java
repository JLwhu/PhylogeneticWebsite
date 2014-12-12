package web.action;


import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import web.exception.ServiceException;
import web.model.Operatelog;
import web.service.SysLogService;
import web.util.OperatorBean;
import web.util.SysConfigSingle;


public class SysLogbean {
	private static SysLogbean sysLogbean = null;
	public final String CREATEACTION="1";
	public final String DELETEACTION="2";
	public final String MODIFYACTION="3";
	public final String LOGINACTION="4";
	public final String LOGOUTACTION="5";
	
	private SysLogbean() {
	}	
	public static SysLogbean getInstance() {
		if (sysLogbean == null) {
			sysLogbean = new SysLogbean();
		}
		return sysLogbean;
	}	
	/*
	 * 
	 */
	public  void writeLog(String operateName, String operateType, String loginfo) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		OperatorBean operatorBean = (OperatorBean) request.getSession()
				.getAttribute(SysConfigSingle.getInstance().SYSUSERINFO);
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext
						.getServletContext());
		SysLogService sysLogService = (SysLogService) wac
				.getBean("sysLogService");
		Operatelog olog=new Operatelog();
		
		olog.setOperatetype(operateType);
		olog.setOperatename(operateName);
		olog.setOperatedate(new Date()); 
		olog.setLoginfo(loginfo);  
		olog.setOperatorid(operatorBean.getUser().getUserid());
		olog.setUsername(operatorBean.getUser().getUsername());
		olog.setDesc("");
		
		try { 
			String operatorid=sysLogService.getMaxID();
			olog.setOperatelogid(Integer.valueOf(operatorid));
			sysLogService.saveSysLog(olog);
		} catch (ServiceException e) {
			
		}catch(java.lang.NullPointerException nullpoint){
			try {
				response.sendRedirect("../index.jsp");
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
	}	
}
