package com.example.inventory.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "Inventory Management System",
        description = "An example inventory management system",
        version = "0.0.1"
))
@Configuration
public class OpenApiConfiguration {
}
