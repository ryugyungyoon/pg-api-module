package com.rc.pgapimodule.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pg")
@Data
public class PGProperties {
	private String defaultPg;

	private KGMobiliansProperties kgmobilians = new KGMobiliansProperties();
	private PaywillProperties paywill = new PaywillProperties();

	@Data
	public static class KGMobiliansProperties {
		private String merchantId;
		private String apiKey;
		private String paymentUrl;
	}

	@Data
	public static class PaywillProperties {
		private String clientId;
		private String clientSecret;
		private String paymentUrl;
	}
}
