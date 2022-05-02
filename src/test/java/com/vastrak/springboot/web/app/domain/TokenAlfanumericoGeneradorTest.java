package com.vastrak.springboot.web.app.domain;

import static org.junit.Assert.assertTrue;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vastrak.springboot.web.app.exceptions.GeneratorIndexOutOfBoundsException;


@SpringBootTest
public class TokenAlfanumericoGeneradorTest {
	
	private static final Logger logger = LogManager.getLogger(TokenAlfanumericoGeneradorTest.class);
	
	private TokenGenerador tokenGeneradorImp = new TokenAlfanumericoGenerador();
	


	@Test
	public void test001_generarTokenLongitudesValidas() {
		
		int longitudMaxima = tokenGeneradorImp.longitudMaxima();
		String tokenMaximo = tokenGeneradorImp.generar(longitudMaxima);
		logger.info("TokenGeneradorImp: "+tokenMaximo);
		
		int longitudCero = 0;
		String tokenCero = tokenGeneradorImp.generar(0);
		String tokenGenerico = tokenGeneradorImp.generar();
		
		assertTrue(tokenMaximo.length() == longitudMaxima);
		assertTrue(tokenCero.length() == longitudCero);
		assertTrue(tokenGenerico.length() == longitudMaxima);
	}
	
	@Test
	public void test002_generarTokenLongitudesNegativa() {
		
		try {
			tokenGeneradorImp.generar(-1);
			Assert.fail("Debió haber lanzado una excepción con índice negativo");
		} catch(GeneratorIndexOutOfBoundsException e) {
		}

	}

	@Test
	public void test003_generarTokenLongitudesMaximaMasUno() {
		
		try {
			tokenGeneradorImp.generar(tokenGeneradorImp.longitudMaxima()+1);
			Assert.fail("Debió haber lanzado una excepción con índice mayor al esperado");
		} catch(GeneratorIndexOutOfBoundsException e) {
		}

	}	

}
