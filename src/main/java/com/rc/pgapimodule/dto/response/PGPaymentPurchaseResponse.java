package com.rc.pgapimodule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentPurchaseResponse {
	private String code;
	private String message;
	private String sign_date;
	private String trade_id;
	private String pay_token;
}
