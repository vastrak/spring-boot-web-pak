package com.vastrak.springboot.web.app.exceptions;

public enum TokenExceptionCause implements ErrorCause {
	
	TOKENID_NO_ENCONTRADO(100, "Token id no encontrado"),
	LONGITUD_INCORRECTA(110, "Longitud del token incorrecta"),
	PARAMETROS_NULOS_O_VACIOS(120, "Los parámetros no pueden ser null o vacíos"),
	LONGITUD_MAX_SUPERADA(130, "La longitud solicitada supera el máximo posible"),
	LONGITUD_NO_PUEDE_SER_NEGATIVA(140, "La longitud no puede ser negativa"),
	ERROR_AL_GUARDAR_TOKEN(150, "Error al guardar el token");
	
	private int error;
	private String mensaje;
	
	private TokenExceptionCause(int error, String mensaje) {
		this.error = error;
		this.mensaje = mensaje;
	}

	public int getError() {
		return error;
	}

	public String getMensaje() {
		return mensaje;
	}
	
}
