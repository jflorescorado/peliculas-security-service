package com.peliculas.prueba.dto;

public class JwtResponse {

	private String token;

	public JwtResponse(String token) {
		super();
		this.token = token;
	}
	
	public JwtResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

