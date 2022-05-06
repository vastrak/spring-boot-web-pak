package com.vastrak.springboot.web.app.services;

import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;

public interface TokenService {
	
	
	public String crearToken(Integer longitud) throws RuntimeException;
	public RetornoResultadoDTO proponerToken(String tokenId, String tokenPropuesto);

}
