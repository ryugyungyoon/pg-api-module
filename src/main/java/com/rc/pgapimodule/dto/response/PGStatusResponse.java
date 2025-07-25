package com.rc.pgapimodule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGStatusResponse {
	private String transactionId;
	private String status;
	private String resultCode;
}
