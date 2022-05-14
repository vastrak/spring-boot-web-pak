package com.vastrak.springboot.web.app.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenComparadorTest {
	
	@Test
	public void test001_tokenVacios() {
		
		TokenComparadorResultado resultado = TokenComparador.comparar("", "");
		
		assertTrue(resultado != null);
		assertTrue(resultado.getBien() == 0 && resultado.getRegular() == 0);
	}

	
	@Test
	public void test002_tokenNull() {
		
		TokenComparadorResultado resultadoNullNull = TokenComparador.comparar(null, null);
		TokenComparadorResultado resultadoNullVacio = TokenComparador.comparar(null, "");
		TokenComparadorResultado resultadoVacioNull = TokenComparador.comparar("", null);
		TokenComparadorResultado resultadoNoVacioNull = TokenComparador.comparar("A", null);
		TokenComparadorResultado resultadoNullNoVacio = TokenComparador.comparar(null, "A");
		
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
		
		
		TokenComparadorResultado resultadoAA = TokenComparador.comparar(tokenA, tokenA);
		TokenComparadorResultado resultadoAB = TokenComparador.comparar(tokenA, tokenB);
		TokenComparadorResultado resultadoAC = TokenComparador.comparar(tokenA, tokenC);
		TokenComparadorResultado resultadoAD = TokenComparador.comparar(tokenA, tokenD);
		
		assertTrue(resultadoAA.getBien() == tokenA.length());
		assertTrue(resultadoAA.getRegular() == 0);
		assertTrue(resultadoAB.getBien() == 0);
		assertTrue(resultadoAA.getRegular() == 0);
		assertTrue(resultadoAC == null);
		assertTrue(resultadoAD.getBien() == 2);
		assertTrue(resultadoAD.getRegular() == 5);
		
	}
	
	
	
}
