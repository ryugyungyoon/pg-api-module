package com.rc.pgapimodule.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("PG API Module")
						.description("PG 연동 API 문서 (KG모빌리언스, 페이윌 등)")
						.version("v1.0.0"));
	}
}
