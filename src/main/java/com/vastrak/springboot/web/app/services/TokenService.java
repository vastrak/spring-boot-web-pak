package com.vastrak.springboot.web.app.services;

import com.vastrak.springboot.web.app.dto.RetornoLongitudDTO;
import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;

public interface TokenService {
	
	
	public String crearToken(Integer longitud);
	public RetornoResultadoDTO proponerToken(String tokenId, String tokenPropuesto);
	public RetornoLongitudDTO obtenerLongitud(String tokenId);

}
