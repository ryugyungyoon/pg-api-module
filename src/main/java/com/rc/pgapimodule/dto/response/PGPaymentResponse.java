package com.rc.pgapimodule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentResponse {
	private boolean success;
	private String transactionId;
	private String redirectUrl;
	private String message;
}
