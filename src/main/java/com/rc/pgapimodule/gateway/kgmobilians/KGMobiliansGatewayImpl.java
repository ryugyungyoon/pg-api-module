package com.rc.pgapimodule.gateway.kgmobilians;

import com.rc.pgapimodule.core.config.PGProperties;
import com.rc.pgapimodule.core.exception.PGException;
import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentCashRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.*;
import com.rc.pgapimodule.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KGMobiliansGatewayImpl implements PaymentGateway {

	private final PGProperties props;
	private final KGMobiliansApiClient apiClient;

	@Override
	public PGPaymentResponse requestPayment(PGPaymentRequest request) {
		try {
			return apiClient.callPaymentApi(request);
		} catch (Exception e) {
			throw new PGException("KG001", "결제 요청 실패", e.getMessage());
		}
	}

	@Override
	public PGPaymentCashResponse requestPaymentCash(PGPaymentCashRequest request) {
		try {
			return apiClient.callPaymentCashApi(request);
		} catch (Exception e) {
			throw new PGException("KG001", "결제 요청 실패", e.getMessage());
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
