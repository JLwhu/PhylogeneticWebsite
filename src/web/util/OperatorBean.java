package web.util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import web.model.Menu;
import web.model.User;

/**
 * @author liuyh
 *
 */
public class OperatorBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperatorBean() {
		super();
	}

	private User user;

	private Long logindate=new Long(0);
	
	private boolean online=false;
	
	private Set<Menu> menus = new HashSet<Menu>();

	public OperatorBean(User user, Set<Menu> menus) {
		super();
		this.user = user;
		this.menus = menus;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getLogindate() {
		return logindate;
	}

	public void setLogindate(Long logindate) {
		this.logindate = logindate;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

}
