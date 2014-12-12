package web.model;

import java.util.Date;

public class Operatelog {
	public Integer getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(Integer operatorid) {
		this.operatorid = operatorid;
	}
	public Operatelog() {
		super();
	}
	private Integer operatelogid;
    private Integer operatorid;
    private String operatename;
    private String operatetype;
    private Date operatedate;
    private String loginfo; 
    private String desc;
    private String username;
	public Integer getOperatelogid() {
		return operatelogid;
	}
	public void setOperatelogid(Integer operatelogid) {
		this.operatelogid = operatelogid;
	}
	public String getOperatename() {
		return operatename;
	}
	public void setOperatename(String operatename) {
		this.operatename = operatename;
	}
	public String getOperatetype() {
		return operatetype;
	}
	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}
	public Date getOperatedate() {
		return operatedate;
	}
	public void setOperatedate(Date operatedate) {
		this.operatedate = operatedate;
	}
	public String getLoginfo() {
		return loginfo;
	}
	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}
	public Operatelog(Integer operatelogid, Integer operatorid,
			String operatename, String operatetype, Date operatedate,
			String loginfo, String deviceid) {
		super();
		this.operatelogid = operatelogid;
		this.operatorid = operatorid;
		this.operatename = operatename;
		this.operatetype = operatetype;
		this.operatedate = operatedate;
		this.loginfo = loginfo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
