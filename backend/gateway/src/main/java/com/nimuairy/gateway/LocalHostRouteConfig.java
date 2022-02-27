package com.nimuairy.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!local-discovery")
public class LocalHostRouteConfig {

	@Bean
	public RouteLocator localhostRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("test", r -> r.path("/test")
						.uri("http://localhost:8081"))
				.build();
	}
}
