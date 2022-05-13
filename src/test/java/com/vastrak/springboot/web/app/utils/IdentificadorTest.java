package com.vastrak.springboot.web.app.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IdentificadorTest {
	
	@Test
	public void test001_longitudTest() {

		String id = Identificador.obtenerId();
		assertNotNull(id);
		assertTrue(id.length() == 12);
		
	}
}
