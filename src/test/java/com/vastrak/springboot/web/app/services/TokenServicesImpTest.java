package com.vastrak.springboot.web.app.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vastrak.springboot.web.app.dto.RetornoLongitudDTO;
import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;
import com.vastrak.springboot.web.app.exceptions.TokenExceptionCause;
import com.vastrak.springboot.web.app.exceptions.TokenServiceException;

@SpringBootTest
public class TokenServicesImpTest {

	@Autowired
	TokenService tokenService;
	
	@Test
	public void test001_crearTokenNulo() {
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.crearToken(null);
	    });
	    assertTrue(TokenExceptionCause.PARAMETROS_NULOS_O_VACIOS.equals(ex.getCausa()));
	}

	@Test
	public void test002_crearTokenVacio() {
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.crearToken(0);
	    });
	    assertTrue(TokenExceptionCause.LONGITUD_NO_PUEDE_SER_NEGATIVA_O_CERO.equals(ex.getCausa()));
	}

	@Test
	public void test003_crearTokenNegativo() {
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.crearToken(-1);
	    });
	    assertTrue(TokenExceptionCause.LONGITUD_NO_PUEDE_SER_NEGATIVA_O_CERO.equals(ex.getCausa()));
	}

	@Test
	public void test004_crearTokenConLongitudExcesiva() {
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.crearToken(Integer.MAX_VALUE);
	    });
	    assertTrue(TokenExceptionCause.LONGITUD_MAX_SUPERADA.equals(ex.getCausa()));
	}

	@Test
	public void test005_proponerCadenaMenor() {
		
		String tokenId = tokenService.crearToken(10);
		String tokenPropuesto = "123456789";
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.proponerToken(tokenId, tokenPropuesto);
	    });
	    assertTrue(TokenExceptionCause.LONGITUD_INCORRECTA.equals(ex.getCausa()));
	}
	
	@Test
	public void test005_proponerCadenaMayor() {
		
		String tokenId = tokenService.crearToken(10);
		String tokenPropuesto = "12345678901";
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.proponerToken(tokenId, tokenPropuesto);
	    });
	    assertTrue(TokenExceptionCause.LONGITUD_INCORRECTA.equals(ex.getCausa()));
	}	

	@Test
	public void test006_buscarPorTokenIdIncorrecto() {
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.proponerToken("AB", "AB");
	    });
	    assertTrue(TokenExceptionCause.TOKENID_NO_ENCONTRADO.equals(ex.getCausa()));
	}	
	
	@Test
	public void test007_ejecucionNormal() {
		
		String tokenId = tokenService.crearToken(10);
		String tokenPropuesto = "ABDCE12345"; 
		RetornoResultadoDTO resultado =	tokenService.proponerToken(tokenId, tokenPropuesto);

		assertNotNull(resultado);
		assertTrue(resultado.getTokenId().equals(tokenId));
				
	}	
	
	@Test
	public void test008_recuperarLongitud() {
		
		int longitud = 10;
		String tokenId = tokenService.crearToken(longitud);
		
		RetornoLongitudDTO retornoLongitud = tokenService.obtenerLongitud(tokenId);
		
		assertNotNull(retornoLongitud);
		assertTrue(retornoLongitud.getTokenId().equals(tokenId));
		assertTrue(retornoLongitud.getLongitud() == longitud);		
	}
	
	
	@Test
	public void test009_recuperarLongitudConTokenIdInexistente() {
		
	    TokenServiceException ex = assertThrows(TokenServiceException.class, () -> {
	    	tokenService.obtenerLongitud("A");
	   	});
	    assertTrue(TokenExceptionCause.TOKENID_NO_ENCONTRADO.equals(ex.getCausa()));
	}	
	
}
