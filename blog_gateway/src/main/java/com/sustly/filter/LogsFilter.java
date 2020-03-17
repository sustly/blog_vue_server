package com.sustly.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author liyue
 * @date 20-3-17 下午4:28
 */
@Configuration
@Slf4j
public class LogsFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info("path: " + request.getPath());
        log.info("address: " + request.getRemoteAddress());
        log.info("method: " + request.getMethodValue());
        log.info("URI: " + request.getURI());
        log.info("Headers: " + request.getHeaders());
        Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
        log.info("body: "+ requestBody);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }


}
