package com.rc.pgapimodule.service;

import com.rc.pgapimodule.dto.*;
import com.rc.pgapimodule.gateway.MockPaymentGateway;
import com.rc.pgapimodule.gateway.PaymentGatewayFactory;
import com.rc.pgapimodule.gateway.kgmobilians.KGMobiliansGatewayImpl;
import com.rc.pgapimodule.gateway.paywill.PaywillGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderPaymentServiceTest {

	private OrderPaymentService paymentService;

	@BeforeEach
	public void setUp() {
		MockPaymentGateway mockGateway = new MockPaymentGateway();

		KGMobiliansGatewayImpl dummyKg = Mockito.mock(KGMobiliansGatewayImpl.class);
		PaywillGatewayImpl dummyPaywill = Mockito.mock(PaywillGatewayImpl.class);

		PaymentGatewayFactory factory = new PaymentGatewayFactory(dummyKg, dummyPaywill, mockGateway);
		paymentService = new OrderPaymentService(factory);
	}

	@Test
	public void 결제_요청_성공_테스트() {
		PGPaymentRequest req = new PGPaymentRequest("ORD123", 5000, "상품명", "홍길동", "test@abc.com", "http://test/return");
		PGPaymentResponse res = paymentService.processPayment(com.rc.pgapimodule.gateway.PGType.MOCK, req);

		assertThat(res.isSuccess()).isTrue();
		assertThat(res.getTransactionId()).startsWith("MOCK-");
	}

	@Test
	public void 결제_승인_성공_테스트() {
		PGApprovalRequest req = new PGApprovalRequest("MOCK-TX-0001", "APPROVE123");
		PGApprovalResponse res = paymentService.confirm(com.rc.pgapimodule.gateway.PGType.MOCK, req);

		assertThat(res.isApproved()).isTrue();
	}

	@Test
	public void 결제_상태_조회_테스트() {
		PGStatusResponse res = paymentService.checkStatus(com.rc.pgapimodule.gateway.PGType.MOCK, "MOCK-TX-0001");
		assertThat(res.getStatus()).isEqualTo("APPROVED");
	}

	@Test
	public void 결제_취소_성공_테스트() {
		PGCancelRequest req = new PGCancelRequest("MOCK-TX-0001", "테스트취소");
		PGCancelResponse res = paymentService.cancel(com.rc.pgapimodule.gateway.PGType.MOCK, req);

		assertThat(res.isCancelled()).isTrue();
	}
}
