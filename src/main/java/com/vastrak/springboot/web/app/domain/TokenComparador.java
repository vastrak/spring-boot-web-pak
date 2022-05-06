package com.vastrak.springboot.web.app.domain;


public class TokenComparador {
	
	/**
	 * Compara dos tokens alfanumericos del tipo String retornando una estructura {@link TokenComparadorResultado}<br>
	 * Si los caracteres de la posición i coinciden en valor, entonces es bien+1<br>
	 * Si los caracteres están en distinta posición, entonces es regular+1<br>
	 * @param referencia
	 * @param propuesto
	 * @return {@link TokenComparadorResultado} contiene x bien e y regular
	 */
	public static TokenComparadorResultado comparar(String referencia, String propuesto) {

		if(referencia != null && propuesto != null && referencia.length() == propuesto.length()) {
			int bien = 0;
			int regular = 0;
			for(int i=0; i<referencia.length(); i++) {
				char caracterReferencia = referencia.charAt(i);
				char caracterPropuesto = propuesto.charAt(i);
				if(caracterReferencia == caracterPropuesto) {
					bien++;
				} else if(referencia.contains(String.valueOf(caracterPropuesto))) {
					regular++;
				}
			}
			return new TokenComparadorResultado(bien, regular);
		}
		return null;		
		
	}
	
	

}
