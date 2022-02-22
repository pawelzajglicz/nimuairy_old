package com.nimuairy.auth.advice;

import com.nimuairy.auth.exception.ErrorMessage;
import com.nimuairy.auth.exception.TokenRefreshException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class TokenControllerAdvice {

	@ExceptionHandler(value = TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		log.info("There was attempt to refresh non existing token");
		return new ErrorMessage(
				HttpStatus.FORBIDDEN.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
	}
}
