package com.rc.pgapimodule.gateway.kgmobilians;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rc.pgapimodule.core.base.CommonUtils;
import com.rc.pgapimodule.core.base.ConstantCode;
import com.rc.pgapimodule.core.config.PGProperties;
import com.rc.pgapimodule.core.config.WebClientConfig;
import com.rc.pgapimodule.core.exception.BusinessException;
import com.rc.pgapimodule.core.exception.ExceptionCode;
import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.PGApprovalResponse;
import com.rc.pgapimodule.dto.response.PGCancelResponse;
import com.rc.pgapimodule.dto.response.PGPaymentResponse;
import com.rc.pgapimodule.dto.response.PGStatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class KGMobiliansApiClient {
	private final PGProperties props;
	private final WebClientConfig webClient;
	private final ObjectMapper objectMapper;

	//KG PG 전문 로그
	private final Logger KgPGTransLogger = LoggerFactory.getLogger("KgPGTransLogger");

	public PGPaymentResponse callPaymentApi(PGPaymentRequest reqDTO) throws JsonProcessingException {
		//Logging
		KgPGTransLogger.info("#{}::RECV[TOBIS->KG:{}]::{}"
				, "KG-mobilians"
				, reqDTO);
		log.info("##### PGPaymentRequest : {}", reqDTO);

		// API 호출
		String result = null;
		try {
			WebClient wc = webClient.getKgClient()
					.mutate()
					.build();

			result = wc.post()
				.uri("/MUP/api/registration")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(reqDTO), PGPaymentRequest.class)
				.retrieve()
				.bodyToMono(String.class)
				.timeout(Duration.ofSeconds(webClient.getTimeout()))
				.block();
		}
		catch (WebClientRequestException e) {
			throw new BusinessException("KG Mobilians 서버 연결 오류 .", ExceptionCode.EXCEPTION_SERVER_CONNECT);
		}
		finally {
			log.info("##### PGApprovalResponse : {}", result);
		}

		// return VO
		try {
			//Logging
			KgPGTransLogger.info("#{}::RECV[TOBIS->KG:{}]::PGPaymentResponse({})"
					, ConstantCode.PAYMENTGATEWAY_CD_KG
					, CommonUtils.nvl(result, "").toString()
							.replace("{", "")
							.replace("}", "")
							.replace("\"", ""));
			return objectMapper.readValue(result, PGPaymentResponse.class);
		}
		catch (JSONException e) {
			throw new BusinessException("가입설계동의 URL 정보 변환 오류.", ExceptionCode.EXCEPTION_PROC_TRANS_DATA);
		}
	}

	public PGApprovalResponse callApprovalApi(PGApprovalRequest req) {
		// 간략화된 예제
		return new PGApprovalResponse(true, "2025-07-15T12:00:00", "성공");
	}

	public PGStatusResponse callStatusApi(String txId) {
		return new PGStatusResponse(txId, "APPROVED", "10000");
	}

	public PGCancelResponse callCancelApi(PGCancelRequest req) {
		return new PGCancelResponse(true, "2025-07-15T12:30:00", "취소 완료");
	}
}
