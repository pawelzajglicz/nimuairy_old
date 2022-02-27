package com.nimuairy.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local-discovery")
public class LoadBalancedRoutesConfig {

	@Bean
	public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("eureka-client", r -> r.path("/test", "/test/**")
						.uri("lb://eureka-client"))
				.build();
	}
}
