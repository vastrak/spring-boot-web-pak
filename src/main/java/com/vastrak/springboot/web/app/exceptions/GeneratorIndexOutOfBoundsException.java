package com.vastrak.springboot.web.app.exceptions;

public class GeneratorIndexOutOfBoundsException extends ArrayIndexOutOfBoundsException  {
	
	private static final long serialVersionUID = 1L;

	public GeneratorIndexOutOfBoundsException() {
		super("El indice se encuentra fuera del rango máximo");
	}
	
	public GeneratorIndexOutOfBoundsException(String mensaje) {
		super(mensaje);
	}

}
