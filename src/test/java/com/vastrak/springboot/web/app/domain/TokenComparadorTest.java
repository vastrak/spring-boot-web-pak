package com.vastrak.springboot.web.app.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenComparadorTest {
	
	
	private TokenComparador tokenComparador = new TokenComparador();

	
	@Test
	public void test001_tokenVacios() {
		
		TokenComparadorResultado resultado = tokenComparador.comparar("", "");
		
		assertTrue(resultado != null);
		assertTrue(resultado.getBien() == 0 && resultado.getRegular() == 0);
	}

	
	@Test
	public void test002_tokenNull() {
		
		TokenComparadorResultado resultadoNullNull = tokenComparador.comparar(null, null);
		TokenComparadorResultado resultadoNullVacio = tokenComparador.comparar(null, "");
		TokenComparadorResultado resultadoVacioNull = tokenComparador.comparar("", null);
		TokenComparadorResultado resultadoNoVacioNull = tokenComparador.comparar("A", null);
		TokenComparadorResultado resultadoNullNoVacio = tokenComparador.comparar(null, "A");
		
		assertTrue(resultadoNullNull == null);
		assertTrue(resultadoNullVacio == null);
		assertTrue(resultadoVacioNull == null);
		assertTrue(resultadoNoVacioNull == null);
		assertTrue(resultadoNullNoVacio == null);
		
	}
	
	
	@Test
	public void test003_tokenIgualesYDistintos() {
		
		String tokenA = "ABCDEFG";
		String tokenB = "HIJKMNO";
		String tokenC = "ABCDEF";
		String tokenD = "ADEFBDG";
		
		
		TokenComparadorResultado resultadoAA = tokenComparador.comparar(tokenA, tokenA);
		TokenComparadorResultado resultadoAB = tokenComparador.comparar(tokenA, tokenB);
		TokenComparadorResultado resultadoAC = tokenComparador.comparar(tokenA, tokenC);
		TokenComparadorResultado resultadoAD = tokenComparador.comparar(tokenA, tokenD);
		
		assertTrue(resultadoAA.getBien() == tokenA.length());
		assertTrue(resultadoAA.getRegular() == 0);
		assertTrue(resultadoAB.getBien() == 0);
		assertTrue(resultadoAA.getRegular() == 0);
		assertTrue(resultadoAC == null);
		assertTrue(resultadoAD.getBien() == 2);
		assertTrue(resultadoAD.getRegular() == 5);
		
	}
	
	
	
}
