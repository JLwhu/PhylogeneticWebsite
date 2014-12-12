package web.exception;

public class UserAlreadyExistsException extends  BaseException  {
	private static final long serialVersionUID = 1L;
	@Override
	public String getDescription() {
		return "User Already Exist!";
	}
	public UserAlreadyExistsException() {
		super();
	}
	
	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
