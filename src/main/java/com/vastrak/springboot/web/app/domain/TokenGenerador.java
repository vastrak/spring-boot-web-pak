package com.vastrak.springboot.web.app.domain;

public interface TokenGenerador {
	
	public String generar();
	public String generar(int longitud);
	public int longitudMaxima();

}
