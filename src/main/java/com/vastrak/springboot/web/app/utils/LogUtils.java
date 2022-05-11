package com.vastrak.springboot.web.app.utils;

import org.apache.logging.log4j.Logger;

public class LogUtils {
	
	/**
	 * Escribe un log en info con el texto + el obj convertido a JSON
	 * @param logger
	 * @param texto
	 * @param obj a convertir a formato JSON
	 */
	public static void escribirLog(Logger logger, String texto, Object obj) {
		
		if(logger != null && texto != null && logger.isInfoEnabled()) {
			String jsonObj = Utils.convertirAJson(obj);
			logger.info(texto+":"+jsonObj);
		}
		
	}

	/**
	 * Escribe un log en info con el texto indicado
	 * @param logger
	 * @param texto
	 */
	public static void escribirLog(Logger logger, String texto) {
		
		if(logger != null && texto != null && logger.isInfoEnabled()) {
			logger.info(texto);
		}
		
	}
	
	
}
