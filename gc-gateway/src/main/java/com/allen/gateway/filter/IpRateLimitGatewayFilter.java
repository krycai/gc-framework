package com.allen.gateway.filter;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author xuguocai 2020/7/7 11:10
 */
//@Component
@Slf4j
public class IpRateLimitGatewayFilter implements GatewayFilter, Ordered {

	int capacity;
	int refillTokens;
	Duration refillDuration;

	public IpRateLimitGatewayFilter(int capacity, int refillTokens, Duration refillDuration) {
		this.capacity = capacity;
		this.refillTokens = refillTokens;
		this.refillDuration = refillDuration;
	}

	private static final Map<String, Bucket> CACHE = new ConcurrentHashMap<>();

	private Bucket createNewBucket() {
		Refill refill = Refill.of(refillTokens, refillDuration);
		Bandwidth limit = Bandwidth.classic(capacity, refill);
		return Bucket4j.builder().addLimit(limit).build();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
		Bucket bucket = CACHE.computeIfAbsent(ip, k -> createNewBucket());

		log.debug("限制过滤器IP: " + ip + ", TokenBucket Available Tokens: " + bucket.getAvailableTokens());
		if (bucket.tryConsume(1)) {
			return chain.filter(exchange);
		} else {
			exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
			return exchange.getResponse().setComplete();
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
