package web.exception;
/**
 * @author lj
 * @version 1.0 
 * @since JDK 1.5
 *
 */
public class DaoException extends BaseException {
	private static final long serialVersionUID = 1L;
	@Override
	public String getDescription() {
		return "Data Operation Exception!";
	}
	public DaoException() {
		super();
	}
	
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
