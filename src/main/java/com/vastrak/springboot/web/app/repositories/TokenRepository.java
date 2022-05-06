package com.vastrak.springboot.web.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vastrak.springboot.web.app.model.Token;

@Repository
public abstract interface TokenRepository extends JpaRepository<Token, Long> {
	
	public abstract Token findByTokenId(String tokenId);

}
