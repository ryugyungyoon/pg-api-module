package com.rc.pgapimodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentPurchaseRequest {
	private String sid;
	private String trade_id;
	private String cash_code;
	private String pay_token;
}
