package com.rc.pgapimodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentRequest {
	private String orderId;
	private int amount;
	private String productName;
	private String buyerName;
	private String buyerEmail;
	private String returnUrl;
}
