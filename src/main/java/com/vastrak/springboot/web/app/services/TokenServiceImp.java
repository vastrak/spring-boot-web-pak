package com.vastrak.springboot.web.app.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastrak.springboot.web.app.domain.TokenAlfanumericoGenerador;
import com.vastrak.springboot.web.app.domain.TokenComparador;
import com.vastrak.springboot.web.app.domain.TokenComparadorResultado;
import com.vastrak.springboot.web.app.domain.TokenGenerador;
import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;
import com.vastrak.springboot.web.app.model.Token;
import com.vastrak.springboot.web.app.repositories.TokenRepository;

@Service
public class TokenServiceImp implements TokenService {

	private static final Logger logger = LogManager.getLogger(TokenServiceImp.class);
	private final TokenGenerador tokenGenerador = new TokenAlfanumericoGenerador();
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public String crearToken(Integer longitud) throws IndexOutOfBoundsException {

		// Validador
		if(longitud != null && !longitudValida(longitud, tokenGenerador.longitudMaxima())) {
			throw new IndexOutOfBoundsException("La longitud del token solicitado es incorrecta");
		}
		
		// Calcular el token según la longitud
		String tokenValor = null;
		if(longitud == null) {
			tokenValor = tokenGenerador.generar();
		} else {
			tokenValor = tokenGenerador.generar(longitud);
		}

		// Asignar un tokenId y guardar la entidad
		Token token = new Token(UUID.randomUUID().toString(), tokenValor);
		Token guardado = tokenRepository.save(token);
		
		return guardado != null ? guardado.getTokenId() : null;
	}

	@Override
	public RetornoResultadoDTO proponerToken(String tokenId, String tokenPropuesto) {

		Token tokenBuscado = tokenRepository.findByTokenId(tokenId);
		TokenComparadorResultado resultado = TokenComparador.comparar(tokenBuscado.getTokenValor(), tokenPropuesto);
		RetornoResultadoDTO retornoResultado = new RetornoResultadoDTO();
		retornoResultado.setTokenId(tokenId);
		retornoResultado.setBien(resultado.getBien());
		retornoResultado.setRegular(resultado.getRegular());
		return retornoResultado;
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
