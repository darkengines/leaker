package leaker.user.exception;

import leaker.core.exception.ManagedException;
import leaker.user.User;

public class UserAlreadyExistsException extends ManagedException {
	private User user;
	public UserAlreadyExistsException(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public int getCode() {
		return 101;
	}
	public String getDescription() {
		return "Occurs when trying to create a new user whose login is already used.";
	}
	public String getMessage() {
		return String.format("Login %s already used", user.getLogin());
	}
}
