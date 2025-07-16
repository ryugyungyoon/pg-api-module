package com.rc.pgapimodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PGCancelResponse {
	private boolean cancelled;
	private String cancelledAt;
	private String message;
}
