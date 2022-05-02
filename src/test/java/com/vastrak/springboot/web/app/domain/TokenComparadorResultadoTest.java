package com.vastrak.springboot.web.app.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TokenComparadorResultadoTest {
	
	
	
	@Test
	public void test002_equal() {
		
		TokenComparadorResultado resultadoCeroCero = new TokenComparadorResultado(0, 0);
		TokenComparadorResultado resultadoUnoCero = new TokenComparadorResultado(1, 0);
		TokenComparadorResultado resultadoCeroUno = new TokenComparadorResultado(0, 1);
		
		assertTrue(resultadoCeroCero.equals(new TokenComparadorResultado(0, 0)));
		assertTrue(resultadoUnoCero.equals(new TokenComparadorResultado(1, 0)));
		assertFalse(resultadoUnoCero.equals(new TokenComparadorResultado(0, 1)));
		assertTrue(resultadoCeroUno.equals(new TokenComparadorResultado(0, 1)));

	}
	
	@Test
	public void test001_gets() {
		
		TokenComparadorResultado resultado = new TokenComparadorResultado(1, 5);
		
		assertTrue(resultado.getBien() == 1);
		assertTrue(resultado.getRegular() == 5);
		
	}
	

}
