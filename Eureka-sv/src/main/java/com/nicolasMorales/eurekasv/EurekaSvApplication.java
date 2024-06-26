package com.nicolasMorales.eurekasv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Nicolas Morales
 * Clase Main del servidor.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaSvApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaSvApplication.class, args);
	}

}
