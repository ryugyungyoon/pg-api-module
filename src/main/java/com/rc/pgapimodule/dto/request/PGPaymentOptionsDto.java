package com.rc.pgapimodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentOptionsDto {
	//간편결제
	private String ep_code;
	private String ep_no;
	private String ep_pay_type;
	private String ep_issue_type;
	private String ep_issue_no;
	private String ep_bill_type;
	private String ep_card_code;
	private String ep_installment;

	//신용카드
	private String cn_installment;
	private String cn_bill_type;
	private String cn_point;
	private String cn_fix_card;
	private String cn_direct;
	private String cn_pay_type;
	private String cn_fix_installment;
	private String cn_easypay_type;
	private String cn_issue_type;
	private String cn_issue_no;
	private String cn_pay_app_use_yn;
	private String cn_pay_app_use_cd;

	//계좌이체
	private String ra_escrow;
	private String ra_escrow_phone_no;
	private String ra_escrow_password;
	private String ra_escrow_receipt;
	private String ra_cash_receipt;
	private String ra_issue_type;
	private String ra_issue_no;
	private String ra_direct;

	//가상계좌;
	private String va_bank_code;
	private String va_deposit_closure;
	private String va_account_closure;
	private String va_cash_receipt;
	private String va_escrow;
	private String va_email;
}
