package com.rc.pgapimodule.controller;

import com.rc.pgapimodule.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/kg/payment")
@Controller
public class KgPaymentController extends BaseController {


	/**
	 * [결제 페이지 이동]
	 *
	 * @author ryuky
	 */
	@GetMapping("add-form")
	public String addForm(Model model){
		return "thymeleaf/kg_mobilians_payment";
	}
}
