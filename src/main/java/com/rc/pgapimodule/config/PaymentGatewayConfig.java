package com.rc.pgapimodule.config;

import com.rc.pgapimodule.gateway.PaymentGatewayFactory;
import com.rc.pgapimodule.gateway.kgmobilians.KGMobiliansGatewayImpl;
import com.rc.pgapimodule.gateway.paywill.PaywillGatewayImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(PGProperties.class)
public class PaymentGatewayConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.setConnectTimeout(Duration.ofSeconds(3))
				.setReadTimeout(Duration.ofSeconds(5))
				.build();
	}

	@Bean
	public PaymentGatewayFactory paymentGatewayFactory(
			KGMobiliansGatewayImpl kg,
			PaywillGatewayImpl pw
	) {
		return paymentGatewayFactory(kg, pw);
	}
}
