package com.nimuairy.auth.security.services;

import com.nimuairy.auth.models.RefreshToken;
import com.nimuairy.auth.payload.request.TokenRefreshRequest;
import com.nimuairy.auth.payload.response.TokenRefreshResponse;

import java.util.Optional;

public interface RefreshTokenService {
	RefreshToken createRefreshToken(Long userId);

	Optional<RefreshToken> findByToken(String token);

	TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);

	RefreshToken verifyExpiration(RefreshToken token);
}
