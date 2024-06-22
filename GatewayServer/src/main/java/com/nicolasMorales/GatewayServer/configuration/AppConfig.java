package com.nicolasMorales.GatewayServer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Value("${config.webclient.auth.url}")
    private String urlServiceAuth;


    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean("auth")
    public WebClient webClientAuth(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(urlServiceAuth).build();
    }
}