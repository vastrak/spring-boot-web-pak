package com.vastrak.springboot.web.app.dto;

public class RetornoResultadoDTO {
	
	private String tokenId;
	private String error;
	private String mensajeError;
	private int bien;
	private int regular;
	
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public int getBien() {
		return bien;
	}
	public void setBien(int bien) {
		this.bien = bien;
	}
	public int getRegular() {
		return regular;
	}
	public void setRegular(int regular) {
		this.regular = regular;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	
	
}
