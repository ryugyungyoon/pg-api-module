package com.rc.exapi.main;

import com.rc.exapi.request.PayNotiRequstDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    /**
     * [타임리프 페이지 예시]
     *
     * @author KyungYoon
     */
    @GetMapping("/thy")
    public String mainThyForm(){
        return "thymeleaf/main";
    }

    /**
     * [jsp 결제 페이지]
     *
     * @author KyungYoon
     */
    @GetMapping("/payRequest")
    public String payRequestForm(){
        return "payRequest";
    }

    /**
     * [jsp 결제 페이지]
     *
     * @author KyungYoon
     */
    @GetMapping("/payRequest-utf")
    public String payRequestUtfForm(){
        return "payRequest_utf";
    }

    /**
     * [jsp 결제 결과 페이지]
     *
     * @author KyungYoon
     */
    @PostMapping("/payResult-utf")
    public String payResultUtfForm(){
        return "payResult_utf";
    }

    /**
     * [jsp 결제 취소 페이지]
     *
     * @author KyungYoon
     */
    @GetMapping("/cancelRequest-utf")
    public String cancelRequestUtfForm(){
        return "cancelRequest_utf";
    }

    /**
     * [jsp 결제 취소 결과 페이지]
     *
     * @author KyungYoon
     */
    @PostMapping("/cancelResult-utf")
    public String payResultfForm(){
        return "cancelResult_utf";
    }

    /**
     * [jsp 결제 조회 페이지] 인증값이 안맞음
     *
     * @author KyungYoon
     */
    @GetMapping("/paySearch")
    public String paySearchForm(){
        return "paySearch";
    }

    /**
     * [jsp 결제 통보 페이지]
     *
     * @author KyungYoon
     */
    @GetMapping("/payNoti-get")
    public String getPayNotiForm(Model model,@ModelAttribute PayNotiRequstDto payNotiRequstDto){
        model.addAttribute("payNotiRequstDto", payNotiRequstDto);
        return "payNoti";
    }

    /**
     * [jsp 결제 통보 페이지]
     *
     * @author KyungYoon
     */
    @PostMapping("/payNoti-post")
    public String postPayNotiForm(){
        return "payNoti";
    }

    /**
     * [영수증 페이지]
     *
     * @author KyungYoon
     */
    @GetMapping("/receipt")
    public String redirectReceipt(){
        //String tid = "nicepay00m01011905171746289297"; // 실제 TID 값 사용
        //String tid = "nicepay00m01012411081606583810"; // 실제 TID 값 사용
        String tid = "nicepay00m01012411111331436392"; // 실제 TID 값 사용
        return "redirect:https://npg.nicepay.co.kr/issue/IssueLoader.do?type=0&TID=" + tid;
    }
}
