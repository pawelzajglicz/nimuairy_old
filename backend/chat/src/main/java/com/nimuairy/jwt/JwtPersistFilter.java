package com.nimuairy.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
@Slf4j
public class JwtPersistFilter extends OncePerRequestFilter {

	private final JWTStore jwtStore;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		jwtStore.saveToken(request.getHeader("Authorization"));
		jwtStore.saveUserId(request.getHeader("User-Id"));

		filterChain.doFilter(request, response);
	}
}
