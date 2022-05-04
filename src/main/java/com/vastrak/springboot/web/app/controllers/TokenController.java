package com.vastrak.springboot.web.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastrak.springboot.web.app.dto.RetornoResultadoDTO;

@RestController
@RequestMapping("/tokens")
public class TokenController {
	
	private static final Logger logger = LogManager.getLogger(TokenController.class);
	
	@GetMapping(path = "/{tokenId}/{tokenPropuesto}")
	public ResponseEntity<RetornoResultadoDTO> getResultadoPorTokenId(@PathVariable(value = "tokenId") String tokenId, @PathVariable(value = "tokenPropuesto") String tokenPropuesto) {
		
		logger.info("tokenId: "+tokenId+" tokenPropuesto: "+tokenPropuesto);
		RetornoResultadoDTO retornoResultadoDTO = new RetornoResultadoDTO();
		retornoResultadoDTO.setTokenId(tokenId);
		retornoResultadoDTO.setBien(10);
		retornoResultadoDTO.setRegular(5);
		return new ResponseEntity<>(retornoResultadoDTO, HttpStatus.OK);
	}
	
	@GetMapping(path = "/nuevo")
	public ResponseEntity<RetornoResultadoDTO> getNuevoToken() {

		String nuevoTokenId = "AMC456";
		logger.info("Retornoamos un nuevo token: "+nuevoTokenId);
		RetornoResultadoDTO retornoResultadoDTO = new RetornoResultadoDTO();
		retornoResultadoDTO.setTokenId(nuevoTokenId);
		retornoResultadoDTO.setBien(0);
		retornoResultadoDTO.setRegular(0);
		return new ResponseEntity<>(retornoResultadoDTO, HttpStatus.OK);
	}

}
