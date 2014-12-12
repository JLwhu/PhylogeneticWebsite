package web.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import web.model.Role;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer userid;
    private String username;
    private String password;
    private String confirmPassword;
	private String phonenumber;
	private String email;
	private Set <Role>storroles = new HashSet<Role>();

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Set<Role> getStorroles() {
		return storroles;
	}

	public void setStorroles(Set<Role> storroles) {
		this.storroles = storroles;
	}


	// Constructors
	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(Integer userid, String username, String password, String phonenumber, String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.phonenumber = phonenumber;
		this.email = email;
	}

}
