package com.vastrak.springboot.web.app.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vastrak.springboot.web.app.dto.RetornoResultadoDTOError;
import com.vastrak.springboot.web.app.utils.LogUtils;

@ControllerAdvice
public class TokenServiceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(TokenServiceExceptionHandler.class);
	
	@ExceptionHandler({ TokenServiceException.class }) 
	public ResponseEntity<RetornoResultadoDTOError> handleTypeTokenServiceException(TokenServiceException ex) {
		
		RetornoResultadoDTOError retornoResultadoDTOError = new RetornoResultadoDTOError();
		retornoResultadoDTOError.setError(ex.getCausa().getError());
		retornoResultadoDTOError.setMensaje(ex.getCausa().getMensaje());
		LogUtils.escribirLog(logger, "[TokenServiceExceptionHandler.handleTypeTokenServiceException]", retornoResultadoDTOError);
		return new ResponseEntity<>(retornoResultadoDTOError, HttpStatus.BAD_REQUEST);
	}
	
	
}
