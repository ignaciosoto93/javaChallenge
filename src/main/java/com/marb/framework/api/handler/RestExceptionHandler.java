package com.marb.framework.api.handler;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.marb.framework.api.dto.ErrorDto;
import com.marb.framework.api.dto.ExceptionResponseDto;
import com.marb.framework.api.exception.BaseRuntimeException;

@ControllerAdvice
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(BaseRuntimeException.class)
	public ResponseEntity<Object> handle(BaseRuntimeException ex) {
		ErrorDto error = ErrorDto.newBuilder().withMessage(ex.getMessage()).withDescription(ex.getDescription())
				.withCode(ex.getDiscriminator() + "-" + ex.getCode()).build();
		logger.error(String.format("following exception has been thrown with error [%s]", error), ex);
		return buildResponseEntity(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, error));
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> invalidInput(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();

		List<ErrorDto> errors = result.getFieldErrors().stream()
				.map(objectError -> ErrorDto.newBuilder().withMessage("Validation Error")
						.withDescription(objectError.getDefaultMessage()).withCode(objectError.getField()).build())
				.collect(Collectors.toList());
		logger.error(String.format("invalid input with errors [%s]", errors), ex);
		return buildResponseEntity(new ExceptionResponseDto(HttpStatus.BAD_REQUEST, errors));

	}

	private ResponseEntity<Object> buildResponseEntity(ExceptionResponseDto exceptionResponse) {
		return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
	}

}