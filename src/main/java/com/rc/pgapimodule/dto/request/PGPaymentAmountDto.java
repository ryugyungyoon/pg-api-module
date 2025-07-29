package com.rc.pgapimodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGPaymentAmountDto {
	private int total;
	private int tax;
	private int tax_free;
	private int supply_value;
}
