package web.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.model.User;
import web.service.LogManageService;
import web.service.MenuMgmtService;
import web.service.RoleMgmtServcie;
import web.service.impl.UserServiceImpl;
import web.model.Menu;

import web.util.MsgObjec;
import web.util.OperatorBean;
import web.model.Role;
import web.exception.ServiceException;
import web.model.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = -389833745243649130L;

    private String username;
    private String password;

    private UserServiceImpl userService;
    
	private Log log = LogFactory.getLog(LoginAction.class);
	private SysLogbean syslogbean = SysLogbean.getInstance();
	private LogManageService logManageService;

	private MenuMgmtService menuMgmtService;
	
	private RoleMgmtServcie roleMgmtServcie;


	private  Map<Long, MsgObjec> msgMap;

	
	private String checkcode;

    public String execute() throws Exception {
        User u = userService.authenticate(username, password);
        if (u == null) {
            return INPUT;
        }

        ActionContext.getContext().getSession().put("user", u);
        return SUCCESS;
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

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    	
	
}
