package com.marb.framework.api.exception;

/**
 * List of errors for the project
 */
public enum IfixError {
	UNKNOWN(1, "Unknown error"), NO_SUCH_ELEMENT_FOUND(2, "No such element found");

	private final Integer code;
	private String message;

	IfixError(Integer code, String message) {

		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
