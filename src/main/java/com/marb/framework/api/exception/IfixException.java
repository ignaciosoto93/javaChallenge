package com.marb.framework.api.exception;

/**
 * Base project exception
 */
public class IfixException extends BaseRuntimeException {

	private static final long serialVersionUID = 1;
	private IfixError ifixError;
	private String description;

	public IfixException(IfixError mainError, String description, Throwable cause) {
		super(description, cause);
		this.ifixError = mainError;
		this.description = description;

	}

	public IfixException(IfixError mainError, String description) {

		this(mainError, description, null);
	}

	@Override
	public Integer getCode() {
		return ifixError.getCode();
	}

	@Override
	public String getMessage() {
		return ifixError.getMessage();
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getDiscriminator() {
		return "CL";
	}

}
