package com.nimuairy.socialnetwork;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FeignClientInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String USER_ID_HEADER = "User-Id";

	private final JWTStore jwtStore;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.header(AUTHORIZATION_HEADER, jwtStore.loadToken());
		requestTemplate.header(USER_ID_HEADER, jwtStore.loadUserId());
	}
}
