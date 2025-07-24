package com.rc.pgapimodule.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Getter
@Configuration
public class WebClientConfig {

	//KG API 요청 url
	@Value("${pg.http.kg-mobilians.url}")
	private String kgUrl;

	//TimeOut
	@Value("${pg.http.common.timeout}")
	private Integer timeout;

	// Default Reactor Http Connector Factory
	@SuppressWarnings("deprecation")
	private ReactorClientHttpConnector defaultReactorClientHttpConnector() {
		HttpClient httpClient = HttpClient.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20*1000)
				.doOnConnected(conn -> conn
						.addHandler(new ReadTimeoutHandler(Duration.ofMillis(20*1000).getSeconds(), TimeUnit.SECONDS))
						.addHandler(new WriteTimeoutHandler(Duration.ofMillis(20*1000).getSeconds(), TimeUnit.SECONDS))
				)
				.responseTimeout(Duration.ofSeconds(10))
				.secure(spec -> spec.sslContext(SslContextBuilder.forClient())
						.defaultConfiguration(SslProvider.DefaultConfigurationType.TCP)
						.handshakeTimeout(Duration.ofSeconds(30))
						.closeNotifyFlushTimeout(Duration.ofSeconds(20))
						.closeNotifyReadTimeout(Duration.ofSeconds(20))
				);

		return new ReactorClientHttpConnector(httpClient);
	}

	@Bean(name = "kgWebClient")
	public WebClient getKgClient() {
		return WebClient.builder()
				.baseUrl(kgUrl)
				.clientConnector(this.defaultReactorClientHttpConnector())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.filter(
						(req, next) -> next.exchange(
								ClientRequest.from(req).header("from", "webclient_by_bbins").build()
						)
				)
				.build();
	}
}