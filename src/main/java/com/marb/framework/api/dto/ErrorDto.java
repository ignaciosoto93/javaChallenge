package com.marb.framework.api.dto;

public class ErrorDto {

	private Object code;
	private String message;
	private String description;

	private ErrorDto(Builder builder) {
		code = builder.code;
		message = builder.message;
		description = builder.description;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Object getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "ErrorDto{" + "code=" + code + ", message='" + message + '\'' + ", description='" + description + '\''
				+ '}';
	}

	public static final class Builder {
		private Object code;
		private String message;
		private String description;

		private Builder() {
		}

		public Builder withCode(Object code) {
			this.code = code;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public ErrorDto build() {
			return new ErrorDto(this);
		}
	}
}
