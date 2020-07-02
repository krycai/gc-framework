//package com.allen.gateway.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.bouncycastle.asn1.ocsp.ResponseData;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
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
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        // 此处写得非常绝对, 只作演示用, 实际中需要采取配置的方式
//        if (getIp(headers).equals("127.0.0.1")) {
//            ServerHttpResponse response = exchange.getResponse();
//
//            byte[] datas = new byte[2];
//            DataBuffer buffer = response.bufferFactory().wrap(datas);
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//            return response.writeWith(Mono.just(buffer));
//        }
//        return chain.filter(exchange);
//    }
//
//    private String getIp(HttpHeaders headers) {
//        return "127.0.0.1";
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
