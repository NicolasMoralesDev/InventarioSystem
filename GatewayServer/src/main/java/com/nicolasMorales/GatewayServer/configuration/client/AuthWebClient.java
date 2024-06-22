package com.nicolasMorales.GatewayServer.configuration.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;

@Service
public class AuthWebClient {

    @Autowired
    @Qualifier("auth")
    private WebClient webClientAuth;

//    @CircuitBreaker(name = "authService", fallbackMethod = "fallbackValidateToken")
    public Mono<Map<String, String>> validarToken(String token) {
        return
                webClientAuth
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/api/auth/validate")
                                .queryParam("token", token)
                                .build())
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {});
    }
//    public ResponseEntity<?> fallbackValidateToken(String token, Exception e) {
//        Map<String,String> resp =new HashMap<>();
//        resp.put("mensaje","no se puedo conectar al servicio auth");
//        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR); // Manejo de fallback en caso de fallo
//    }
}