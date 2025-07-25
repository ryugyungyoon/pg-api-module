package com.rc.pgapimodule.controller;

import com.rc.pgapimodule.dto.request.PGApprovalRequest;
import com.rc.pgapimodule.dto.request.PGCancelRequest;
import com.rc.pgapimodule.dto.request.PGPaymentRequest;
import com.rc.pgapimodule.dto.response.*;
import com.rc.pgapimodule.gateway.PGType;
import com.rc.pgapimodule.service.OrderPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Tag(name = "Payment API", description = "PG사별 결제 연동 API")
public class OrderPaymentController {

	private final OrderPaymentService paymentService;

	@PostMapping("/request")
	@Operation(summary = "결제 요청")
	public CommonResponse<PGPaymentResponse> requestPayment(
			@RequestParam PGType pgType,
			PGPaymentRequest request) {
		PGPaymentResponse result = paymentService.processPayment(pgType, request);
		return CommonResponse.ok(result);
	}

	@PostMapping("/confirm")
	@Operation(summary = "결제 승인")
	public CommonResponse<PGApprovalResponse> confirmPayment(
			@RequestParam PGType pgType,
			@RequestBody PGApprovalRequest request) {
		return CommonResponse.ok(paymentService.confirm(pgType, request));
	}

	@GetMapping("/status")
	@Operation(summary = "결제 상태 조회")
	public CommonResponse<PGStatusResponse> status(@RequestParam PGType pgType, @RequestParam String txId) {
		return CommonResponse.ok(paymentService.checkStatus(pgType, txId));
	}

	@PostMapping("/cancel")
	@Operation(summary = "결제 취소")
	public CommonResponse<PGCancelResponse> cancel(@RequestParam PGType pgType, @RequestBody PGCancelRequest req) {
		return CommonResponse.ok(paymentService.cancel(pgType, req));
	}
}
