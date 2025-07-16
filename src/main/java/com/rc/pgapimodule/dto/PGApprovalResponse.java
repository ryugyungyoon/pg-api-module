package com.rc.pgapimodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGApprovalResponse {
	private boolean approved;
	private String approvedAt;
	private String message;
}
