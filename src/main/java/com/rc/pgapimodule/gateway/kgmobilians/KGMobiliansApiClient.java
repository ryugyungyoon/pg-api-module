package com.rc.pgapimodule.gateway.kgmobilians;

import com.rc.pgapimodule.config.PGProperties;
import com.rc.pgapimodule.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class KGMobiliansApiClient {
	private final PGProperties.KGMobiliansProperties props;
	private final RestTemplate restTemplate;

	public PGPaymentResponse callPaymentApi(PGPaymentRequest req) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-Api-Key", props.getApiKey());

		HttpEntity<PGPaymentRequest> entity = new HttpEntity<>(req, headers);
		ResponseEntity<PGPaymentResponse> response = restTemplate.exchange(
			props.getPaymentUrl(), HttpMethod.POST, entity, PGPaymentResponse.class);

		return response.getBody();
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
