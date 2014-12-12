package web.action;

import web.model.User;
import web.exception.UserAlreadyExistsException;
import web.service.HotspotService;
import web.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
    private static final long serialVersionUID = 7021982816578678150L;

    private User user;

    private UserService userService;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
	public UserService getUserService() {
		return userService;
	}

    public String execute() throws Exception {
        try {
        	if (user == null) System.out.print("user null");
            userService.addUser(user);
        } catch (UserAlreadyExistsException e) {
            return INPUT;
        }
        return SUCCESS;
    }


}
