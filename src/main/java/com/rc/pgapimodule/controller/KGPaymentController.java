package com.rc.pgapimodule.controller;

import com.rc.pgapimodule.core.base.BaseController;
import com.rc.pgapimodule.dto.response.PGPaymentAuthResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/kg/payment")
@Controller
public class KGPaymentController extends BaseController {


	/**
	 * [결제 페이지 이동]
	 *
	 * @author ryuky
	 */
	@GetMapping("add-form")
	public String addForm(Model model){
		return "thymeleaf/kg_mobilians_payment";
	}

	/**
	 * [결제 성공 페이지 이동]
	 *
	 * @author ryuky
	 */
	@PostMapping("success")
	public String paymentSuccess(Model model, PGPaymentAuthResponse reqDTO) {
		model.addAttribute("paymentAuth", reqDTO);
		return "thymeleaf/kg_mobilians_payment_success";
	}

	/**
	 * [결제 실패 페이지 이동]
	 *
	 * @author ryuky
	 */
	@PostMapping("fail")
	public String paymentFail() {
		return "thymeleaf/kg_mobilians_payment_close";
	}

	/**
	 * [결제 취소 페이지 이동]
	 *
	 * @author ryuky
	 */
	@GetMapping("cancel-close")
	public String paymentCancelClose() {
		return "thymeleaf/kg_mobilians_payment_close";
	}
}
