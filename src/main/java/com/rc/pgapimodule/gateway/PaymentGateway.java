package com.rc.pgapimodule.gateway;

import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentCashRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.*;

public interface PaymentGateway {
	PGPaymentResponse requestPayment(PGPaymentRequest request);
	PGPaymentCashResponse requestPaymentCash(PGPaymentCashRequest request);
	PGApprovalResponse confirmPayment(PGApprovalRequest request);
	PGStatusResponse inquirePaymentStatus(String transactionId);
	PGCancelResponse cancelPayment(PGCancelRequest request);
}
