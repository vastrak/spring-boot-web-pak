package com.vastrak.springboot.web.app.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String tokenId;
	private String tokenValor;
	
	
	public Token() {
		
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(tokenId, other.tokenId);
	}

	@Override
	public String toString() {
		return "Token [id=" + id + 
				", tokenId=" + tokenId + 
				", tokenValor=" + tokenValor + "]";
	}
	
}
