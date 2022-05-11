package com.vastrak.springboot.web.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vastrak.springboot.web.app.model.Token;

public abstract interface TokenRepository extends JpaRepository<Token, Long> {

	public abstract Token findByTokenId(String tokenId);
}
