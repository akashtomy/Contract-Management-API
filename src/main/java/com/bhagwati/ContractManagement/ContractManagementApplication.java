package com.bhagwati.ContractManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The type Contract management application.
 *
 * @author Akash Thomas.
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Contract Management API", version = "2.0", description = "Contract Information"))
@EnableJpaAuditing
public class ContractManagementApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ContractManagementApplication.class, args);
	}

}
