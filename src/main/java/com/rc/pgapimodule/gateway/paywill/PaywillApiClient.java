package com.rc.pgapimodule.gateway.paywill;

import com.rc.pgapimodule.config.PGProperties;
import com.rc.pgapimodule.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PaywillApiClient {

	private final PGProperties props;
    private final RestTemplate restTemplate;

    public PGPaymentResponse callPaymentApi(PGPaymentRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(props.getPaywill().getClientId(), props.getPaywill().getClientSecret());

        HttpEntity<PGPaymentRequest> entity = new HttpEntity<>(req, headers);
        ResponseEntity<PGPaymentResponse> response = restTemplate.exchange(
            props.getPaywill().getPaymentUrl(), HttpMethod.POST, entity, PGPaymentResponse.class);

        return response.getBody();
    }

    public PGApprovalResponse callApprovalApi(PGApprovalRequest req) {
        return new PGApprovalResponse(true, "2025-07-15T12:10:00", "페이윌 승인 완료");
    }

    public PGStatusResponse callStatusApi(String txId) {
        return new PGStatusResponse(txId, "CONFIRMED", "7000");
    }

    public PGCancelResponse callCancelApi(PGCancelRequest req) {
        return new PGCancelResponse(true, "2025-07-15T12:40:00", "페이윌 취소 완료");
    }
}
