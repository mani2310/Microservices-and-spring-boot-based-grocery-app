package com.user.management.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(GenericExceptionHandler.class);
	
	@ExceptionHandler(CustomException.class)
	public void handleCustomException(HttpServletResponse res,CustomException ex) throws IOException
	{
		logger.error("ERROR", ex);
		res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	}
	
	 @ExceptionHandler(AccessDeniedException.class)
	    public void handleAccessDeniedException(HttpServletResponse res, AccessDeniedException e) throws IOException {
	        logger.error("ERROR", e);
	        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
	    }

	    @ExceptionHandler(IllegalArgumentException.class)
	    public void handleIllegalArgumentException(HttpServletResponse res, IllegalArgumentException e) throws IOException {
	        logger.error("ERROR", e);
	        res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
	    }

	    @ExceptionHandler(Exception.class)
	    public void handleException(HttpServletResponse res, Exception e) throws IOException {
	        logger.error("ERROR", e);
	        res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
	    }
}
