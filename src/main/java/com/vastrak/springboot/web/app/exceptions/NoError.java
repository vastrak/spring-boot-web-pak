package com.vastrak.springboot.web.app.exceptions;

public enum NoError implements ErrorCause {
	
	OP_CORRECTA(0, "Operación realizada correctamente");

	private int error;
	private String mensaje;
	
	private NoError(int error, String mensaje) {
		this.error = error;
		this.mensaje = mensaje;
	}

	@Override
	public int getError() {
		return error;
	}

	@Override
	public String getMensaje() {
		return mensaje;
	}

	
}
