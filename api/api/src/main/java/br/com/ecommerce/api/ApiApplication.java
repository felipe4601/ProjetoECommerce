package br.com.ecommerce.api;

import br.com.ecommerce.api.model.Cliente;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Ecommerce",
				description = "API desenvolvida no curso de backend do SENAI em Java com Spring"
		)
)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


}
