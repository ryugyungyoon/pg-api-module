package com.rc.pgapimodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGCancelRequest {
	private String sid;
	private String trade_id;
	private String cash_code;
	private String pay_token;
	private String amount;
	private String cancel_type;
	private String part_cancel;
	private String hmac;
	private String cn_tax_ver;
	private String bill_type;
	private String tax;
	private String tax_free;
	private String tax_amount;
	private String divide_payment;
	private String bank_code;
	private String account_no;
	private String account_name;
	private String refund_amount;
}
