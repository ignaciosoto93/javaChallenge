package com.marb.framework.api.dto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponseDto {

	private HttpStatus status;
	private Long timestamp;
	private List<ErrorDto> errors;

	private ExceptionResponseDto() {
		timestamp = ZonedDateTime.now().toEpochSecond();
	}

	public ExceptionResponseDto(HttpStatus status, ErrorDto error) {
		this();
		this.status = status;
		errors = new ArrayList<>();
		errors.add(error);
	}

	public ExceptionResponseDto(HttpStatus status, List<ErrorDto> errors) {
		this();
		this.status = status;
		this.errors = new ArrayList<>(errors);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public List<ErrorDto> getErrors() {
		return errors;
	}
}
