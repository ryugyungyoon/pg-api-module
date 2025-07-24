package com.rc.pgapimodule.gateway.paywill;

import com.rc.pgapimodule.config.PGProperties;
import com.rc.pgapimodule.dto.*;
import com.rc.pgapimodule.exception.PGException;
import com.rc.pgapimodule.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaywillGatewayImpl implements PaymentGateway {

	private final PGProperties props;
	private final PaywillApiClient apiClient;

	@Override
	public PGPaymentResponse requestPayment(PGPaymentRequest request) {
		try {
			return apiClient.callPaymentApi(request);
		} catch (Exception e) {
			throw new PGException("PW001", "페이윌 결제 요청 실패", e.getMessage());
		}
	}

	@Override
	public PGApprovalResponse confirmPayment(PGApprovalRequest request) {
		return apiClient.callApprovalApi(request);
	}

	@Override
	public PGStatusResponse inquirePaymentStatus(String transactionId) {
		return apiClient.callStatusApi(transactionId);
	}

	@Override
	public PGCancelResponse cancelPayment(PGCancelRequest request) {
		return apiClient.callCancelApi(request);
	}
}
