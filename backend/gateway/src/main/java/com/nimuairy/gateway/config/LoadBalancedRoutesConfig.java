package com.nimuairy.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@AllArgsConstructor
@Configuration
@Profile("local-discovery")
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
				.build();
	}
}
