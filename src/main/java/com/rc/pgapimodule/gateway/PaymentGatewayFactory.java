package com.rc.pgapimodule.gateway;

import com.rc.pgapimodule.gateway.kgmobilians.KGMobiliansGatewayImpl;
import com.rc.pgapimodule.gateway.paywill.PaywillGatewayImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGatewayFactory {
	private final KGMobiliansGatewayImpl kgGateway;
	private final PaywillGatewayImpl paywillGateway;
	private final MockPaymentGateway mockGateway;

	public PaymentGateway getGateway(PGType type) {
		return switch (type) {
			case KG_MOBILIANS -> kgGateway;
			case PAYWILL -> paywillGateway;
			case MOCK -> mockGateway;
		};
	}
}
