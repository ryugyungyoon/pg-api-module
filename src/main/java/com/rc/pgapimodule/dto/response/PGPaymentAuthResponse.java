package com.rc.pgapimodule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentAuthResponse {
	private String code;
	private String message;
	private String sid;
	private String tid;
	private String gign_date;
	private String cash_code;
	private String pay_token;
	private String product_name;
	private String amount;
	private String deposit;
	private String mstr;
	private String hmac;

	//휴대폰
	private String phone_no;
	private String mc_bill_key;
	private String mc_user_key;
	private String mc_simple_key;

	//신용카드
	private String cn_installment;
	private String cn_card_no;
	private String cn_card_code;
	private String cn_card_name;
	private String cn_appr_on;
	private String cn_bill_key;
	private String cn_coupon_price;
	private String cn_pay_method;
	private String cn_own_cd;
	private String cn_bill_yn;

	//간편결제
	private String ep_installment;
	private String ep_card_no;
	private String ep_card_code;
	private String ep_card_name;
	private String ep_appr_no;
	private String ep_coupon_price;
	private String ep_pay_method;
	private String ep_bill_yn;

	//계좌이체
	private String bank_name;
	private String cash_receipt;
	private String ra_bill_yn;
	private String ra_reference_key;

	//가상계좌
	private String bank_code;
	private String account_no;
	private String deposit_closure;
	private String va_cash_receipt;
	private String va_name;
	private String va_bill_yn;
	private String va_reference_key;
}
