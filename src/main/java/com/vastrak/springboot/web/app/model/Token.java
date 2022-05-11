package com.vastrak.springboot.web.app.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="tokenId", nullable=false, unique=true)
	private String tokenId;
	@Column(name="tokenValor", nullable=false)
	private String tokenValor;
	
	
	public Token() {
	}
	

	public Token(String tokenId, String tokenValor) {
		this.tokenId = tokenId;
		this.tokenValor = tokenValor;
	}

	// La clave de la tabla sólo tiene get
	public Long getId() {
		return id;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenValor() {
		return tokenValor;
	}

	public void setTokenValor(String tokenValor) {
		this.tokenValor = tokenValor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tokenId);
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj != null && obj instanceof Token) {
			Token other = (Token) obj;
			return other.getTokenId() != null && other.getTokenId().equals(this.tokenId);
		}
		return false;
		
	}

	@Override
	public String toString() {
		return "Token [id=" + id + 
				", tokenId=" + tokenId + 
				", tokenValor=" + tokenValor + "]";
	}
	
}
