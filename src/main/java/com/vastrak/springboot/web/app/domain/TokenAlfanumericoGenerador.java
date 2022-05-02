package com.vastrak.springboot.web.app.domain;

import java.io.Serializable;
import java.util.Random;

import com.vastrak.springboot.web.app.exceptions.GeneratorIndexOutOfBoundsException;

public class TokenAlfanumericoGenerador implements TokenGenerador, Serializable {

	private static final long serialVersionUID = 1L;
	private final String TOKENBASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final int TOKENMAXLONG = TOKENBASE.length();
	
	
	public String generar() {
		return generar(TOKENMAXLONG);
	}
	
	public String generar(int longitud) throws GeneratorIndexOutOfBoundsException {

		if(longitud < 0 || longitud > TOKENMAXLONG) {
			throw new GeneratorIndexOutOfBoundsException();
		}
		
		StringBuilder token = new StringBuilder();
		Random random = new Random(System.currentTimeMillis());   
		for(int i=0; i<longitud;) {
			char elegido = TOKENBASE.charAt(random.nextInt(TOKENMAXLONG));
			if(!existeCaracterEnToken(token, elegido)) {
				token.append(elegido);
				i++;
			}
		}
		return token.toString();
	}
	
	/**
	 * 
	 * @param token
	 * @param caracter
	 * @return
	 */
	private boolean existeCaracterEnToken(StringBuilder token, char caracter) {
		
		 if(token!=null) {
			 for(int i=0; i<token.length();i++) {
				 if(caracter == token.charAt(i)) {
					 return true;
				 }
			 }
		 }
		 return false;
	}
	
	public int longitudMaxima() {
		return TOKENMAXLONG;
	}

}
