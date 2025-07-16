package com.rc.pgapimodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGCancelRequest {
	private String transactionId;
	private String reason;
}
