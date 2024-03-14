package de.fi105.nachweiseBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class NachweiseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NachweiseBackendApplication.class, args);
	}

}
