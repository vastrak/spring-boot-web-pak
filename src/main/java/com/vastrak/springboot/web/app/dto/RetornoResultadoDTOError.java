package com.vastrak.springboot.web.app.dto;

import com.vastrak.springboot.web.app.exceptions.ErrorCause;

public class RetornoResultadoDTOError implements ErrorCause {
	
	private int error;
	private String mensaje;

	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
