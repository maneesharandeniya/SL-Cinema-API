package com.slcinema.models;

import javax.validation.constraints.NotBlank;

public class AuthenticationResponse {
	
	@NotBlank(message = "jwt is mandatory")
	private String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
	
}
