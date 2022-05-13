package com.vastrak.springboot.web.app.utils;

import java.util.UUID;

public class Identificador {
	
	
	public static String obtenerId()  {
		
		String[] token = UUID.randomUUID().toString().split("-");
		return token[token.length-1];
		
	}

}
