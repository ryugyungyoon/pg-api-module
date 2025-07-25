package com.rc.pgapimodule;

import com.rc.pgapimodule.core.config.PGProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PGProperties.class)
public class ExapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExapiApplication.class, args);
	}

}
