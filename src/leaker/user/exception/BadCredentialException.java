package leaker.user.exception;

import leaker.core.exception.ManagedException;
import leaker.user.User;

public class BadCredentialException extends ManagedException {
	private User user;
	public BadCredentialException(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public int getCode() {
		return 102;
	}
	public String getDescription() {
		return "Occurs when cannot find user with given credential";
	}
	public String getMessage() {
		return String.format("Bad credential for user %s", user.getLogin());
	}
}
