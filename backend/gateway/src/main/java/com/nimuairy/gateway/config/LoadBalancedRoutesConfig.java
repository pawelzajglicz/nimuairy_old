package com.nimuairy.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@AllArgsConstructor
@Configuration
public class LoadBalancedRoutesConfig {

	AuthenticationFilter filter;

	@Bean
	public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("eureka-client", r -> r.path("/test", "/test/**")
						.uri("lb://eureka-client"))
				.route("nim-auth", r -> r.path("/auth/**")
						.filters(f -> f.filter(filter))
						.uri("lb://nim-auth"))
				.route("nim-auth", r -> r.path("/users/**")
						.filters(f -> f.filter(filter))
						.uri("lb://nim-auth"))
				.route("nim-chat", r -> r.path("/chat/**")
						.filters(f -> f.filter(filter))
						.uri("lb://nim-chat"))
				.route("nim-chat", r -> r.path("/messaging/**")
						.filters(f -> f.filter(filter))
						.uri("lb:ws://nim-chat"))
				.route("nim-social-network", r -> r.path("/social-network/**")
						.filters(f -> f.filter(filter))
						.uri("lb://nim-social-network"))
				.build();
	}
}
