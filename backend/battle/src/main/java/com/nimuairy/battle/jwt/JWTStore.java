package com.nimuairy.battle.jwt;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JWTStore {

	private String token;
	private String userId;

	public String loadToken() {
		return token;
	}

	public String loadUserId() {
		return userId;
	}

	public void saveToken(String token) {
		this.token = token;
	}

	public void saveUserId(String userId) {
		this.userId = userId;
	}
}
