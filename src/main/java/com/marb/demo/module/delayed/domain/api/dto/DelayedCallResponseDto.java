package com.marb.demo.module.delayed.domain.api.dto;

import com.marb.demo.module.delayed.processor.DelayedIntegrationProcessor;

/**
 * A generic response from calling a {@link DelayedIntegrationProcessor}
 *
 * @author anbernas
 */
public class DelayedCallResponseDto {

	private ResponseStatus status;
	private String message;

	public DelayedCallResponseDto(ResponseStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	private DelayedCallResponseDto(Builder builder) {
		status = builder.status;
		message = builder.message;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return "DelayedCallResponse [status=" + status + ", message=" + message + "]";
	}

	public boolean isSuccessful() {
		return status == ResponseStatus.SUCCESS;
	}

	public boolean isFailedButRetry() {
		return status == ResponseStatus.RETRY;
	}

	public boolean isHardFail() {
		return status == ResponseStatus.FAILURE;
	}

	public String getMessage() {
		return message;
	}

	public enum ResponseStatus {
		SUCCESS, RETRY, FAILURE
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DelayedCallResponseDto other = (DelayedCallResponseDto) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public static final class Builder {
		private ResponseStatus status;
		private String message;

		private Builder() {
		}

		public Builder withStatus(ResponseStatus status) {
			this.status = status;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public DelayedCallResponseDto build() {
			return new DelayedCallResponseDto(this);
		}
	}
}
