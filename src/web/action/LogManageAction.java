package web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.exception.ServiceException;
import web.model.Menu;
import web.model.Role;
import web.model.User;
import web.service.LogManageService;
import web.service.MenuMgmtService;
import web.service.RoleMgmtServcie;
import web.util.MsgObjec;
import web.util.OperatorBean;
import web.util.SysConfigSingle;

/**
 *
 * 
 * @author liuyh
 * 
 */
public class LogManageAction extends ActionSupport {
	private Log log = LogFactory.getLog(LogManageAction.class);
	private static final long serialVersionUID = 1L;
	private SysLogbean syslogbean = SysLogbean.getInstance();
	private LogManageService logManageService;

	private MenuMgmtService menuMgmtService;
	
	private RoleMgmtServcie roleMgmtServcie;

	private String username;

	private String message;
	private  Map<Long, MsgObjec> msgMap;

	private String password;
	
	private String checkcode;
	public void replaceFromString(String string1, String string2, String string3) {

		String newString = Pattern.compile(string2, Pattern.LITERAL).matcher(
				string1).replaceAll(string3);
		System.out.println(newString);

	}
	/**
	 * 
	 * 
	 * @return
	 */
	public String loginAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String returnStr="MainIndex";
		try {
			OperatorBean operatorBean = findoperator();
			if(operatorBean.getUser()==null){
				this.setMessage("Username or password error!");
				return ERROR;				
			}						
			String loginCode= (String)session.getAttribute("LOGIN_CODE");			
			if(loginCode!=null && !loginCode.equals(checkcode)){
				this.setMessage("checkcode error!");
				return ERROR;					
			}			
	        ServletContext context = request.getSession().getServletContext();
			
	        HttpSession oldsession =(HttpSession) context.getAttribute(operatorBean.getUser().getUserid().toString());
			
			if (oldsession != null &&  request.getSession(false)!=null) 
			{ 

				oldsession.invalidate();// 
			}
			session = request.getSession();
			session.setAttribute(SysConfigSingle.getInstance().SYSUSERINFO,
					operatorBean);			
			context.setAttribute(operatorBean.getUser().getUserid().toString(), session);			
			String loginfo = operatorBean.getUser().getUsername() + " ";
			String operateName = "login";
/*			if(msgMap==null){
				msgMap = (Map<Long, MsgObjec>)context.getAttribute("msgMap");
				if(msgMap==null){
					msgMap = new HashMap<Long, MsgObjec>();
				}
			}			
			
			synchroCipmap(context,msgMap);*/
						
			Set<Role> roleset=operatorBean.getUser().getStorroles();
			Iterator roleit=roleset.iterator();
			
			while(roleit.hasNext()){
				
				Role role=(Role)roleit.next();
				if(role.getRolename().equals("SysOperator")){
					returnStr="operator";
					break;
				}else {
					returnStr="MainIndex";
				}
				
				
			}
			
			session.setAttribute("returnStr", returnStr);
			syslogbean.writeLog("Login", syslogbean.LOGINACTION, loginfo);
			log.info(loginfo);
			
		} catch (ServiceException e) {
			request.setAttribute("ErrMsg","Login Error!");
			this.setMessage("Login Error!");
			return ERROR;
		}						
		return returnStr;
	}
	
	private OperatorBean findoperator() throws ServiceException {
		User user = null;
		OperatorBean operatorBean = new OperatorBean();
		Set<Menu> menus = new HashSet<Menu>();
		List<Role> roles = new ArrayList<Role>();
		user = logManageService.getLogByLognamePwd(username, password);
		if (user != null) {
			if (user.getUsername().equals("admin")) {
				//
				List menulist;
				menulist = menuMgmtService.findAllMenuList();
				for (int i = 0; i < menulist.size(); i++) {
					Menu object = (Menu) menulist.get(i);
					menus.add(object);
				}
			} else {
				//
				roles = roleMgmtServcie.findRolesByUserid(user.getUserid());
				Iterator roleit = roles.iterator();
				while (roleit.hasNext()) {
					Role role = (Role) roleit.next();
					// Set<Vodbomenu> rmenus = role.getStormenus();
					List<Menu> rmenus = menuMgmtService
							.findStormenusByRoleId(role.getRoleid());
					Iterator menuit = rmenus.iterator();
					while (menuit.hasNext()) {
						Menu tempmenu = (Menu) menuit.next();
						if (!checkExist(menus, tempmenu)) {
							menus.add(tempmenu);
						}
					}
				}
			}
			Long logindate = System.currentTimeMillis();
			operatorBean.setMenus(menus);
			operatorBean.setUser(user);
			operatorBean.setLogindate(logindate);
			operatorBean.setOnline(true);
		}
		return operatorBean;
	}
	
	private boolean checkExist(Set<Menu> menus,Menu menu){
		
		Iterator menuit = menus.iterator();
		while(menuit.hasNext()){
			Menu tempmenu=(Menu) menuit.next();
			if(tempmenu.getMenuId().equals(menu.getMenuId())){
				return true;				
			}
		
		}
		
		return false;
		
	}
		
	/**
	 * 
	 * 
	 * @return
	 */
	public String logoutAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		OperatorBean operatorBean = (OperatorBean) session
				.getAttribute(SysConfigSingle.getInstance().SYSUSERINFO);
		if (operatorBean != null) {
			String loginfo = operatorBean.getUser().getUsername() + "Logout";
			String operateName = "Logout";
			syslogbean.writeLog(operateName, syslogbean.LOGOUTACTION, loginfo);
		       if(session.isNew()!=true){			
			ServletContext sc = session.getServletContext();
			sc.removeAttribute(operatorBean.getUser().getUserid().toString());
		              session.invalidate();
		       }
		}
		
		return SUCCESS;
	}
	private synchronized void synchroCipmap(ServletContext context,Map<Long, MsgObjec> msgMap){
		context.setAttribute("msgMap",msgMap);
	} 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LogManageService getLogManageService() {
		return logManageService;
	}

	public void setLogManageService(LogManageService logManageService) {
		this.logManageService = logManageService;
	}    
    
    
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MenuMgmtService getMenuMgmtService() {
		return menuMgmtService;
	}

	public void setMenuMgmtService(MenuMgmtService menuMgmtService) {
		this.menuMgmtService = menuMgmtService;
	}
	public RoleMgmtServcie getRoleMgmtServcie() {
		return roleMgmtServcie;
	}
	public void setRoleMgmtServcie(RoleMgmtServcie roleMgmtServcie) {
		this.roleMgmtServcie = roleMgmtServcie;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
}
