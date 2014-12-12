package web.exception;
/**
 * @author lj
 * @version 1.0 
 * @since JDK 1.5
 *
 */
public abstract class BaseException extends Exception {
	abstract public String getDescription();
	public BaseException() {
		super();
	}
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}
