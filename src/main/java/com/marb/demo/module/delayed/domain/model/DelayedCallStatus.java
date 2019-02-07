package com.marb.demo.module.delayed.domain.model;

import java.util.Arrays;

/**
 * Available statuses for calls
 *
 * @author anbernas
 */
public enum DelayedCallStatus {

	//MAX 16 CHARS!
	PENDING("PENDING"),

	IN_PROGRESS("IN_PROGRESS"),

	RETRY("RETRY"),

	FAILED("FAILED"),

	PROCESSED("PROCESSED");

	static final int MAX_CODE_CHARS = 16;
	private String code;

	private DelayedCallStatus(String code) {
		if (code.length() > MAX_CODE_CHARS) {
			throw new IllegalArgumentException("Code must be at most " + MAX_CODE_CHARS + " chars");
		}
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public static DelayedCallStatus byCode(String code) {
		DelayedCallStatus status = Arrays.stream(values()).filter(delayedCallStatus -> delayedCallStatus.equals(code))
				.findFirst().orElse(null);

		return status;
	}

}
