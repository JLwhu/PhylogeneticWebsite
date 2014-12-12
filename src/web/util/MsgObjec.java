package web.util;

/**
 * cmdtype 
 * object
 * @author liuyuhui
 *
 */
public class MsgObjec {
	/**
	 * @param cmdtype
	 * @param object
	 */
	public MsgObjec(String cmdtype, Object object) {
		super();
		this.cmdtype = cmdtype;
		this.object = object;
	}
	public MsgObjec() {
	}
	private String cmdtype;
	private Object object;
	public String getCmdtype() {
		return cmdtype;
	}
	public void setCmdtype(String cmdtype) {
		this.cmdtype = cmdtype;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

}
