package com.rc.pgapimodule.gateway;

import com.rc.pgapimodule.dto.request.*;
import com.rc.pgapimodule.dto.response.*;

public interface PaymentGateway {
	PGPaymentResponse requestPayment(PGPaymentRequest request);
	PGPaymentCashResponse requestPaymentCash(PGPaymentCashRequest request);
	PGPaymentPurchaseResponse requestPaymentPurchase(PGPaymentPurchaseRequest request);
	PGApprovalResponse confirmPayment(PGApprovalRequest request);
	PGStatusResponse inquirePaymentStatus(String transactionId);
	PGCancelResponse cancelPayment(PGCancelRequest request);
}
