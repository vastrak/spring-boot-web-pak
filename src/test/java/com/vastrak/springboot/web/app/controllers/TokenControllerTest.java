package com.vastrak.springboot.web.app.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrak.springboot.web.app.dto.RetornoLongitudDTO;
import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;
import com.vastrak.springboot.web.app.services.TokenService;
import com.vastrak.springboot.web.app.utils.Utils;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TokenController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TokenControllerTest {

	@MockBean
	private TokenService tokenServiceMock;
	
	@Autowired
	private MockMvc tokenController;
	
	@Test
	public void test001_getNuevoToken() throws Exception {
		
		// http://localhost:8080/tokens/nuevo
		String tokenId = "m8j7cp12";
		when(tokenServiceMock.crearToken(16)).thenReturn(tokenId);
		
		this.tokenController.perform(MockMvcRequestBuilders
				.post("/tokens/nuevo")
				.content(Utils.convertirAJson(16))
				.contentType("application/json")
				.param("tokenId", tokenId))
				.andExpect(status().isOk());

	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void test002_getResultadoPorTokenId() throws Exception {

		// http://localhost:8080/tokens/m8j7cp12/1234567890
		String tokenId = "m8j7cp12";
		String tokenPropuesto = "1234567890";
		
		RetornoResultadoDTO resultado = new RetornoResultadoDTO();
		resultado.setTokenId(tokenId);
		resultado.setLongitud(tokenPropuesto.length());
		resultado.setRegular(0);
		resultado.setBien(0);
		
		when(tokenServiceMock.proponerToken(tokenId, tokenPropuesto)).thenReturn(resultado);
		
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("tokenId", resultado.getTokenId());
		requestParams.add("longitud", Integer.toString(resultado.getLongitud()));
		requestParams.add("bien", Integer.toString(resultado.getBien()));
		requestParams.add("regular", Integer.toString(resultado.getRegular()));
		
		this.tokenController.perform(get("/tokens/"+tokenId+"/"+tokenPropuesto)
				.contentType("application/json")
				.params(requestParams))
				.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void test003_getLongitud() throws Exception {
		
		// http://localhost:8080/tokens/longitud/m8j7cp12
		String tokenId = "m8j7cp12";
		String tokenPropuesto = "1234567890";		
		
		RetornoLongitudDTO retornoLongitud = new RetornoLongitudDTO();
		retornoLongitud.setTokenId(tokenId);
		retornoLongitud.setLongitud(tokenPropuesto.length());
		
		when(tokenServiceMock.obtenerLongitud(tokenId)).thenReturn(retornoLongitud);
		
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("tokenId", retornoLongitud.getTokenId());
		requestParams.add("longitud", Integer.toString(retornoLongitud.getLongitud()));
		
		this.tokenController.perform(get("/tokens/longitud/"+tokenId)
				.contentType("application/json")
				.params(requestParams))
				.andExpect(status().isOk());
		
	}
	
	
}
