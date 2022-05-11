package com.vastrak.springboot.web.app.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	private static final Logger logger = LogManager.getLogger(Utils.class);
	
	public static String convertirAJson(Object objeto) {

		String json = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			logger.error("[Utils.convertirAJson] Error al convertir objeto a JSON");
		}
		return json;
	}

}
