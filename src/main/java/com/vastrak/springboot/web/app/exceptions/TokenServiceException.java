package com.vastrak.springboot.web.app.exceptions;

public class TokenServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private ErrorCause causa;
	
	public TokenServiceException() {
		super();
	}
	
	public TokenServiceException(String mensaje) {
		super(mensaje);
	}
	
	public TokenServiceException(ErrorCause causa) {
		super(causa.getMensaje());
		this.causa = causa;
	}
	
	public ErrorCause getCausa() {
		return causa;
	}
	
}
