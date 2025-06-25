package com.sarlym.gearflowapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "Gearflow API",
				version = "1",
				description = "API desenvolvida para sistema de emissão de ordem de serviços e gerenciamento de mecânica"
		)
)
@SpringBootApplication
public class GearflowpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GearflowpiApplication.class, args);
	}

}
