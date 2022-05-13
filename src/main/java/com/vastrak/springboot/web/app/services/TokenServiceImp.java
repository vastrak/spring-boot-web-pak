package com.vastrak.springboot.web.app.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.vastrak.springboot.web.app.domain.TokenAlfanumericoGenerador;
import com.vastrak.springboot.web.app.domain.TokenComparador;
import com.vastrak.springboot.web.app.domain.TokenComparadorResultado;
import com.vastrak.springboot.web.app.domain.TokenGenerador;
import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;
import com.vastrak.springboot.web.app.exceptions.ErrorCause;
import com.vastrak.springboot.web.app.exceptions.NoError;
import com.vastrak.springboot.web.app.exceptions.TokenExceptionCause;
import com.vastrak.springboot.web.app.exceptions.TokenServiceException;
import com.vastrak.springboot.web.app.model.Token;
import com.vastrak.springboot.web.app.repositories.TokenRepository;
import com.vastrak.springboot.web.app.utils.Identificador;
import com.vastrak.springboot.web.app.utils.LogUtils;

@Service
public class TokenServiceImp implements TokenService {

	private static final Logger logger = LogManager.getLogger(TokenServiceImp.class);
	private final TokenGenerador tokenGenerador = new TokenAlfanumericoGenerador();
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public String crearToken(Integer longitud) throws TokenServiceException {

		// Validador
		ErrorCause tokenExceptionCause = validarParametroEntrada(longitud);
		if(tokenExceptionCause != null) {
			LogUtils.escribirLog(logger, tokenExceptionCause.getMensaje());
			throw new TokenServiceException(tokenExceptionCause);
		}
		
		String tokenValor = tokenGenerador.generar(longitud);
		Token token = new Token(Identificador.obtenerId(), tokenValor);
		Token guardado = tokenRepository.save(token);
		if(guardado == null) {
			LogUtils.escribirLog(logger, TokenExceptionCause.ERROR_AL_GUARDAR_TOKEN.getMensaje());
			throw new TokenServiceException(TokenExceptionCause.ERROR_AL_GUARDAR_TOKEN);
		}
		
		return guardado.getTokenId();
	}

	/**
	 * @param longitud
	 * @return 
	 */
	private ErrorCause validarParametroEntrada(Integer longitud) {
		
		if(longitud == null) {
			return TokenExceptionCause.PARAMETROS_NULOS_O_VACIOS;
		} else if(longitud <= 0) {
			return TokenExceptionCause.LONGITUD_NO_PUEDE_SER_NEGATIVA;
		} else if(!longitudValida(longitud, tokenGenerador.longitudMaxima())) {
			return TokenExceptionCause.LONGITUD_MAX_SUPERADA;
		}		
		return null;
	}
	
	
	@Override
	public RetornoResultadoDTO proponerToken(String tokenId, String tokenPropuesto) throws TokenServiceException {

		if(ObjectUtils.isEmpty(tokenId) || ObjectUtils.isEmpty(tokenPropuesto)) {
			LogUtils.escribirLog(logger, TokenExceptionCause.PARAMETROS_NULOS_O_VACIOS.getMensaje());
			throw new TokenServiceException(TokenExceptionCause.PARAMETROS_NULOS_O_VACIOS);
		}
		
		Token tokenBuscado = tokenRepository.findByTokenId(tokenId);
		
		if(tokenBuscado == null) {
			LogUtils.escribirLog(logger, TokenExceptionCause.TOKENID_NO_ENCONTRADO.getMensaje());
			throw new TokenServiceException(TokenExceptionCause.TOKENID_NO_ENCONTRADO);
		} else if(tokenBuscado.getTokenValor().length() != tokenPropuesto.length()) {
			LogUtils.escribirLog(logger, TokenExceptionCause.LONGITUD_INCORRECTA.getMensaje());
			throw new TokenServiceException(TokenExceptionCause.LONGITUD_INCORRECTA);
		}
		
		TokenComparadorResultado resultado = TokenComparador.comparar(tokenBuscado.getTokenValor(), tokenPropuesto);
		RetornoResultadoDTO retornoResultado = new RetornoResultadoDTO();
		retornoResultado.setMensajeError(NoError.OP_CORRECTA.getMensaje());
		retornoResultado.setError(NoError.OP_CORRECTA.getError());
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
