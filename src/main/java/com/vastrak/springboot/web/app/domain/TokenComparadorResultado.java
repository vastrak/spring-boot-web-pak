package com.vastrak.springboot.web.app.domain;

public final class TokenComparadorResultado {
	
	private final int bien;
	private final int regular;
	
	public TokenComparadorResultado(int bien, int regular) {
		this.bien = bien;
		this.regular = regular;
	}

	public int getBien() {
		return bien;
	}

	public int getRegular() {
		return regular;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof TokenComparadorResultado) {
			TokenComparadorResultado token = (TokenComparadorResultado) obj;
			return token.getBien() == bien && token.getRegular() == regular;
		}
		return false;
	}

}
