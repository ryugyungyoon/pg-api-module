package com.rc.pgapimodule.gateway;

import com.rc.pgapimodule.dto.*;

public interface PaymentGateway {
	PGPaymentResponse requestPayment(PGPaymentRequest request);
	PGApprovalResponse confirmPayment(PGApprovalRequest request);
	PGStatusResponse inquirePaymentStatus(String transactionId);
	PGCancelResponse cancelPayment(PGCancelRequest request);
}
