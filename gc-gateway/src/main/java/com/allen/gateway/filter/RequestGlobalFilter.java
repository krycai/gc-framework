//package com.allen.gateway.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * 全局过滤器
// * @author xuguocai 2020/6/30 13:55
// */
//@Slf4j
//@Component
//public class RequestGlobalFilter implements GlobalFilter, Ordered {
//
//    /**
//     * 执行逻辑
//     * @param exchange
//     * @param chain
//     * @return
//     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest serverHttpRequest= exchange.getRequest();
//        String uri = serverHttpRequest.getURI().toString();
//        log.info("请求路径:{}",uri);
//
//        String method = serverHttpRequest.getMethodValue();
//        log.info("请求方法:{}",method);
//
//        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
//        return exchange.getResponse().setComplete();
//    }
//
//    /**
//     * 执行顺序
//     * @return
//     */
//    @Override
//    public int getOrder() {
//        return 1;
//    }
//
//}
