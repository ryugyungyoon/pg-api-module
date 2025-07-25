package com.rc.pgapimodule.gateway;

import com.rc.pgapimodule.core.code.PGStatusCode;
import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.PGApprovalResponse;
import com.rc.pgapimodule.dto.response.PGCancelResponse;
import com.rc.pgapimodule.dto.response.PGPaymentResponse;
import com.rc.pgapimodule.dto.response.PGStatusResponse;
import org.springframework.stereotype.Component;

@Component
public class MockPaymentGateway implements PaymentGateway {
	@Override
	public PGPaymentResponse requestPayment(PGPaymentRequest request) {
		return new PGPaymentResponse(true, "MOCK-TX-0001", "https://mock-payment/redirect", "테스트 결제");
	}

	@Override
	public PGApprovalResponse confirmPayment(PGApprovalRequest request) {
		return new PGApprovalResponse(true, "2025-07-15T12:00:00", "테스트 승인 완료");
	}

	@Override
	public PGStatusResponse inquirePaymentStatus(String transactionId) {
		return new PGStatusResponse(transactionId, PGStatusCode.APPROVED.name(), "0000");
	}

	@Override
	public PGCancelResponse cancelPayment(PGCancelRequest request) {
		return new PGCancelResponse(true, "2025-07-15T12:10:00", "테스트 취소 완료");
	}
}
