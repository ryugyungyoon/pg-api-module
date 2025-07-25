package com.rc.pgapimodule.gateway;

import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.PGApprovalResponse;
import com.rc.pgapimodule.dto.response.PGCancelResponse;
import com.rc.pgapimodule.dto.response.PGPaymentResponse;
import com.rc.pgapimodule.dto.response.PGStatusResponse;

public interface PaymentGateway {
	PGPaymentResponse requestPayment(PGPaymentRequest request);
	PGApprovalResponse confirmPayment(PGApprovalRequest request);
	PGStatusResponse inquirePaymentStatus(String transactionId);
	PGCancelResponse cancelPayment(PGCancelRequest request);
}
