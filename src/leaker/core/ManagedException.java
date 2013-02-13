package leaker.core;

@SuppressWarnings("serial")
public final class ManagedException extends Exception {
	private int code;
	private String message;
	public ManagedException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
