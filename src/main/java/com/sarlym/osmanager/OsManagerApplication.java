package com.sarlym.osmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "OS Manager API",
				version = "1",
				description = "API desenvolvida para sistema de emissão de ordem de serviços"
		)
)
@SpringBootApplication
public class OsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsManagerApplication.class, args);
	}

}
