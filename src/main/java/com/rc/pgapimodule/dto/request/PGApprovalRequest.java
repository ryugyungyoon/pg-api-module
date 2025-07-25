package com.rc.pgapimodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGApprovalRequest {
	private String transactionId;
	private String approvalCode;
}
