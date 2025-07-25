package com.rc.pgapimodule.service;

import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.PGApprovalResponse;
import com.rc.pgapimodule.dto.response.PGCancelResponse;
import com.rc.pgapimodule.dto.response.PGPaymentResponse;
import com.rc.pgapimodule.dto.response.PGStatusResponse;
import com.rc.pgapimodule.gateway.PGType;
import com.rc.pgapimodule.gateway.PaymentGateway;
import com.rc.pgapimodule.gateway.PaymentGatewayFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPaymentService {

	private final PaymentGatewayFactory gatewayFactory;

	public PGPaymentResponse processPayment(PGType type, PGPaymentRequest request) {
		PaymentGateway gateway = gatewayFactory.getGateway(type);
		return gateway.requestPayment(request);
	}

	public PGApprovalResponse confirm(PGType type, PGApprovalRequest request) {
		return gatewayFactory.getGateway(type).confirmPayment(request);
	}

	public PGStatusResponse checkStatus(PGType type, String txId) {
		return gatewayFactory.getGateway(type).inquirePaymentStatus(txId);
	}

	public PGCancelResponse cancel(PGType type, PGCancelRequest request) {
		return gatewayFactory.getGateway(type).cancelPayment(request);
	}
}
