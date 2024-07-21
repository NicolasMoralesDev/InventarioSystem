package com.nicolasMorales.InventariumSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Nicolas Morales
 * Clase main del servidor.
 */
@SpringBootApplication
public class InventariumSystemApplication {

	private static Logger logger = LoggerFactory.getLogger(InventariumSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventariumSystemApplication.class, args);
		logger.info("Servidor iniciado!");
	}

}
