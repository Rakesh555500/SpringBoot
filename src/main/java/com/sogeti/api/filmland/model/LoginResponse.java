package com.sogeti.api.filmland.model;

import java.io.Serializable;

public class LoginResponse extends ApiResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String accessToken;

	public LoginResponse(String jwttoken) {
		this.accessToken = jwttoken;
	}

	public String getToken() {
		return this.accessToken;
	}
}