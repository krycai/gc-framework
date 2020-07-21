package com.allen.gateway.config;

import java.time.Duration;

import com.allen.gateway.factory.ElapsedGatewayFilterFactory;
import com.allen.gateway.filter.IpRateLimitGatewayFilter;
import com.allen.gateway.filter.TokenFilter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuguocai 2020/7/21 15:23
 */
@Slf4j
@Configuration
public class RouteLocatorConfig {

//	@Autowired
	private IpRateLimitGatewayFilter ipRateLimitGatewayFilter;

	@Bean
	public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
		log.info("----------------route配置---------------------");
		return builder.routes()
				.route(r -> r.path("/admin/**")
						.filters(f -> f.stripPrefix(1)
								.filter(new IpRateLimitGatewayFilter(10, 1, Duration.ofSeconds(1)))
								)
						.uri("lb://GC-ADMIN")
						.order(0)
						.id("gc-admin")
				)
				.build();
		// @formatter:on
	}

//	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}

	@Bean
	public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new ElapsedGatewayFilterFactory();
	}
}
