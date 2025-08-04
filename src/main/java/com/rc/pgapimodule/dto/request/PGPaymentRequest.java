package com.rc.pgapimodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentRequest {
	private String sid;
	private String cash_code;
	private String product_name;
	private String trade_id;
	private PGPaymentAmountDto amount;
	private String site_url;
	private String ok_url;
	private String call_type;
	private String close_url;
	private String fail_url;
	private String integrated_pay;
	private String divide_payment;
	private List<PGPaymentDivideDto> divide_payment_list;
	private PGPaymentOptionsDto pay_options;
}
