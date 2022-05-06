package com.vastrak.springboot.web.app.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.vastrak.springboot.web.app.domain.TokenAlfanumericoGenerador;
import com.vastrak.springboot.web.app.domain.TokenComparador;
import com.vastrak.springboot.web.app.domain.TokenComparadorResultado;
import com.vastrak.springboot.web.app.domain.TokenGenerador;
import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;

@Service
public class TokenServiceImp implements TokenService {

	private static final Logger logger = LogManager.getLogger(TokenServiceImp.class);
	private final TokenGenerador tokenGenerador = new TokenAlfanumericoGenerador();
	
	
	@Override
	public String crearToken(Integer longitud) throws IndexOutOfBoundsException {
		
		if(longitud != null && !longitudValida(longitud, tokenGenerador.longitudMaxima())) {
			throw new IndexOutOfBoundsException("La longitud del token solicitado es incorrecta");
		} else if(longitud == null) {
			return agregarToken(UUID.randomUUID().toString(), tokenGenerador.generar());
		}
		return agregarToken(UUID.randomUUID().toString(), tokenGenerador.generar(longitud));
	}

	@Override
	public RetornoResultadoDTO proponerToken(String tokenId, String tokenPropuesto) {

		String token = recuperarToken(tokenId);
		TokenComparadorResultado resultado = TokenComparador.comparar(token, tokenPropuesto);
		RetornoResultadoDTO retornoResultado = new RetornoResultadoDTO();
		retornoResultado.setTokenId(tokenId);
		retornoResultado.setBien(resultado.getBien());
		retornoResultado.setRegular(resultado.getRegular());
		return retornoResultado;
	}
	
	
	

	
	
	private String agregarToken(String tokenId, String token) {
		
		return "ABC";
	}
	

	private String recuperarToken(String tokenId) {

		return crearTablaTokens().get(tokenId);
	}
	
	
	private Map<String, String> crearTablaTokens() {
		
		Map<String, String> tablaTokens = new HashMap<String, String>();
		tablaTokens.put("ABC", "ABCDEFG12345");
		tablaTokens.put("CDE", "GHIJKLM12345");
		return tablaTokens;
	}
	
	/**
	 * 
	 * @param longitud
	 * @param longitudMaxima
	 * @return
	 */
	private boolean longitudValida(Integer longitud, Integer longitudMaxima) {
		
		return longitud != null && longitud.compareTo(longitudMaxima) <= 0;
	}
}
