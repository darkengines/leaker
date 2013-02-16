package leaker.core.exception;

public abstract class ManagedException extends Exception {
	public int getCode() {
		return 666;
	}
	public String getDescription() {
		return "N/A";
	}
	public String getMessage() {
		return super.getMessage();
	}
}
