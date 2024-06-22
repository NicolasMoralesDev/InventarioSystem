package com.nicolasMorales.GatewayServer.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndPoints=List.of(
            "/api/auth/login",
            "/api/auth/validate"
    );
//    podemos agregar /eureka si habilitamos eureka en el apigateway

    public Predicate<ServerHttpRequest> isSecured=
            request-> openApiEndPoints
                    .stream()
                    .noneMatch(uri-> request.getURI().getPath().contains(uri));
}