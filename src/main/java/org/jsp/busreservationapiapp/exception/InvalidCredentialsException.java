package org.jsp.busreservationapiapp.exception;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Invalid Phone or Email or Password";
	}
}
