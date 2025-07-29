package com.rc.pgapimodule.gateway;

import com.rc.pgapimodule.core.code.PGStatusCode;
import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentCashRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.*;
import org.springframework.stereotype.Component;

@Component
public class MockPaymentGateway implements PaymentGateway {
	@Override
	public PGPaymentResponse requestPayment(PGPaymentRequest request) {
		return new PGPaymentResponse("0000", "정상처리", "000730010001", "20190619160850826790", "https://mup.mobilians.co.kr/MUP/api/payment.mcash?tid=1233523523523", "https://mup.mobilians.co.kr/MUP/api/qr-code.mcash?tid=1233523523523", "I3qi5h256KJKTbbKAlC9pXFiVaAgb/E2ci6ZgkjzVsg=", "20190401090010");
	}

	@Override
	public PGPaymentCashResponse requestPaymentCash(PGPaymentCashRequest request) {
		return new PGPaymentCashResponse(true, "https://mock-payment/redirect");
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
