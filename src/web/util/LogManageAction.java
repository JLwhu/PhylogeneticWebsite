package web.util;

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
import web.action.SysLogbean;

/**
 * 登录及注销ACTION
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

	private String logname;

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
	 * 管理员登录验证
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
				this.setMessage("用户名或密码有误!");
				return ERROR;				
			}						
			String loginCode= (String)session.getAttribute("LOGIN_CODE");			
			if(loginCode!=null && !loginCode.equals(checkcode)){
				this.setMessage("验证码有误!");
				return ERROR;					
			}			
	        ServletContext context = request.getSession().getServletContext();
			
	        HttpSession oldsession =(HttpSession) context.getAttribute(operatorBean.getUser().getUserid().toString());
			
			if (oldsession != null &&  request.getSession(false)!=null) 
			{ 
//			 第二次登陆后第一次无效 
				oldsession.invalidate();// 清除第一次登陆的session 
			}
			session = request.getSession();
			session.setAttribute(SysConfigSingle.getInstance().SYSUSERINFO,
					operatorBean);			
			context.setAttribute(operatorBean.getUser().getUserid().toString(), session);//放入当前最新session 			
			String loginfo = operatorBean.getUser().getUsername() + "管理员登录系统";
			String operateName = "登录";
/*			if(msgMap==null){//如果当前为空，去上下文中取
				msgMap = (Map<Long, MsgObjec>)context.getAttribute("msgMap");
				if(msgMap==null){//如果上下文中没有，新建一个
					msgMap = new HashMap<Long, MsgObjec>();
				}
			}			
			
			synchroCipmap(context,msgMap);*/
						
			Set<Role> roleset=operatorBean.getUser().getStorroles();
			Iterator roleit=roleset.iterator();
			
			while(roleit.hasNext()){
				
				Role role=(Role)roleit.next();
				if(role.getRolename().equals("运营管理员")){
					returnStr="operator";
					break;
				}else if(role.getRolename().equals("审核管理员")){
					returnStr="audit";
					break;
				}else {
					returnStr="MainIndex";
				}
				
				
			}
			
			session.setAttribute("returnStr", returnStr);
			syslogbean.writeLog("登录", syslogbean.LOGINACTION, loginfo);
			log.info(loginfo);
			
		} catch (ServiceException e) {
			request.setAttribute("ErrMsg","系统登录异常,请找管理员!");
			this.setMessage("系统登录异常,请找管理员!");
			return ERROR;
		}						
		return returnStr;
	}
	
	private OperatorBean findoperator() throws ServiceException {
		User user = null;
		OperatorBean operatorBean = new OperatorBean();
		Set<Menu> menus = new HashSet<Menu>();
		List<Role> roles = new ArrayList<Role>();	
			user = logManageService.getLogByLognamePwd(logname, password);
			if(user!=null){
			if (user.getUsername().equals("admin")) {
                //判断是否是系统管理员admin，是就列出全部功能模块
				List menulist;
					menulist = menuMgmtService.findAllMenuList();
					for (int i = 0; i < menulist.size(); i++) {
						Menu object = (Menu) menulist.get(i);
						menus.add(object);
					}
			} else {
				//根据角色，然后判断角色的权限，然后载入可操作模块。
				roles = roleMgmtServcie.findRolesByUserid(user.getUserid());//获得角色
				Iterator roleit = roles.iterator();
				while(roleit.hasNext()){
						Role role = (Role) roleit.next();
						//Set<Vodbomenu> rmenus = role.getStormenus();
						List<Menu>  rmenus = menuMgmtService.findStormenusByRoleId(role.getRoleid());
						Iterator menuit = rmenus.iterator();
						while(menuit.hasNext()){
							Menu tempmenu=(Menu) menuit.next();
							if(!checkExist(menus,tempmenu)){
								menus.add(tempmenu);
							}
					}
				}
			}
			Long logindate= System.currentTimeMillis();
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
	 * 管理员退出
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
			String loginfo = operatorBean.getUser().getUsername() + "管理员退出系统";
			String operateName = "退出";
			syslogbean.writeLog(operateName, syslogbean.LOGOUTACTION, loginfo);
		       if(session.isNew()!=true){			
			ServletContext sc = session.getServletContext();
			sc.removeAttribute(operatorBean.getUser().getUserid().toString());
		              session.invalidate();
		       }
		}// 记录日志
		
		return SUCCESS;
	}
	private synchronized void synchroCipmap(ServletContext context,Map<Long, MsgObjec> msgMap){
		context.setAttribute("msgMap",msgMap);
	} 
	
	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
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
