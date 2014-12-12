package web.util;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class OnlineUserListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {		
		 System.out.println("sessionCreated!");		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
		ServletContext sc = session.getServletContext();
		OperatorBean operatorBean = (OperatorBean) session
				.getAttribute(SysConfigSingle.getInstance().SYSUSERINFO);
        if(operatorBean!=null){		
		sc.removeAttribute(operatorBean.getUser().getUserid().toString());
        }
		
	}
}
