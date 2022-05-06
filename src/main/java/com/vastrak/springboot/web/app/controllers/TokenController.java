package com.vastrak.springboot.web.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;
import com.vastrak.springboot.web.app.services.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {
	
	private static final Logger logger = LogManager.getLogger(TokenController.class);
	
	@Autowired
	private TokenService tokenService; // Mock
	
	
	@GetMapping(path = "/{tokenId}/{tokenPropuesto}")
	public ResponseEntity<RetornoResultadoDTO> getResultadoPorTokenId(@PathVariable(value = "tokenId") String tokenId, @PathVariable(value = "tokenPropuesto") String tokenPropuesto) {
		
		logger.info("tokenId: "+tokenId+" tokenPropuesto: "+tokenPropuesto);
		RetornoResultadoDTO retornoResultado = tokenService.proponerToken(tokenId, tokenPropuesto);
		return new ResponseEntity<>(retornoResultado, HttpStatus.OK);
	}
	
	@GetMapping(path = "/nuevo/{longitud}")
	public ResponseEntity<RetornoResultadoDTO> getNuevoToken(@PathVariable(value = "longitud") Integer longitud) {

		String tokenId = tokenService.crearToken(Integer.valueOf(longitud));
		logger.info("Retornamos un nuevo tokenId: "+tokenId);
		RetornoResultadoDTO retornoResultadoDTO = new RetornoResultadoDTO();
		retornoResultadoDTO.setTokenId(tokenId);
		retornoResultadoDTO.setBien(0);
		retornoResultadoDTO.setRegular(0);
		return new ResponseEntity<>(retornoResultadoDTO, HttpStatus.OK);
	}

}
