package com.vastrak.springboot.web.app.services;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenServicesImpTest {

	@Autowired
	TokenService tokenService;
	
	
	@Test
	public void test001_crearTokenInvalidos() {
		
	    assertThrows(IndexOutOfBoundsException.class, () -> {
	    	tokenService.crearToken(null);
	    });
	    assertThrows(IndexOutOfBoundsException.class, () -> {
	    	tokenService.crearToken(-1);
	    });
	    assertThrows(IndexOutOfBoundsException.class, () -> {
	    	tokenService.crearToken(0);
	    });
	    assertThrows(IndexOutOfBoundsException.class, () -> {
	    	tokenService.crearToken(Integer.MAX_VALUE);
	    });
		
	}
	

	
	
}
