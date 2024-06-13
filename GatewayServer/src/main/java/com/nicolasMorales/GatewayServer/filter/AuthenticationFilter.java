package com.nicolasMorales.GatewayServer.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

    @Autowired
    private RouteValidator validator;
    @Autowired
    private AuthWebClient authWebClient;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            String token = null;
            if (validator.isSecured.test(exchange.getRequest())){
                //validar si el header tiene el token
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    return onError(exchange,HttpStatus.UNAUTHORIZED);
                }
                String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader!=null && authHeader.startsWith("Bearer ")){
                    token=authHeader.substring(7);
                }
                try {
                    authWebClient.validarToken(token);
                }catch (RuntimeException ex){
                    throw new RuntimeException("ERROR conexion con authweclient");
                }
            }
            return chain.filter(exchange);
        }));
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
        ServerHttpResponse response=exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }
    public static class Config{
    }
}