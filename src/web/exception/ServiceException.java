package web.exception;
/**
 * Service
 * @author lj
 * @version 1.0 
 * @since JDK 1.5
 *
 */
public class ServiceException extends BaseException {
	private static final long serialVersionUID = 1L;
	@Override
	public String getDescription() {
		return "Service Layer Exception!";
	}
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
