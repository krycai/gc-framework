package com.allen.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 限流操作  限流提供了基于 Redis 的实现
 * @author xuguocai 2020/7/2 13:16
 */
@Configuration
public class KeyResolverConfig {

    /**
     *  IP 限流
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    public static String getIpAddr(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        List<String> ips = headers.get("X-Forwarded-For");
        String ip = "192.168.1.1";
        if (ips != null && ips.size() > 0) {
            ip = ips.get(0);
        }
        return ip;
    }

    /**
     * 用户限流
     *   根据用户来做限流只需要获取当前请求的用户 ID 或者用户名
     * @return
     */
   // @Bean
    KeyResolver userKeyResolver() {
        return exchange ->
                Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    /**
     * 接口限流
     *  获取请求地址的 uri 作为限流 Key
     * @return
     */
   // @Bean
    KeyResolver apiKeyResolver() {
        return exchange ->
                Mono.just(exchange.getRequest().getPath().value());
    }
}
