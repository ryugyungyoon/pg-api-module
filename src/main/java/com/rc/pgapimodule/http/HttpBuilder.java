package com.rc.pgapimodule.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rc.pgapimodule.exception.BusinessException;
import com.rc.pgapimodule.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * POST 요청에 대한 Response 를 만들어 주는 클래스
 *
 * @author jklee
 */
@Slf4j
@Component
public class HttpBuilder {

	public static final String MESSAGE_PREFIX = "error.message.";
	public static final String SUCCESS_CODE = "200";
	public static final String FAIL_CODE = "500";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ObjectMapper objectMapper;

	/*==============================================================================
	 * 일반 요청 관련
	 ==============================================================================*/
	/**
	 * object 리턴
	 *
	 * @author jklee
	 * @return ResponseVO
	 */
	public ResponseVO resultOne(String returnKey, Object resData){
		Map<String, Object> resMap = new HashMap<>();
		resMap.put(returnKey, resData);
		return makeResponse(resMap);
	}

	/**
	 * object list 리턴
	 *
	 * @author jklee
	 * @return ResponseVO
	 */
	public ResponseVO resultList(Object resData){
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("list", resData);
		return makeResponses(resMap);
	}

	/**
	 * 페이징 list 리턴
	 *
	 * @author jklee
	 * @return ResponseVO
	 */
	public ResponseVO resultPagingList(Object resData, Object pagingData){
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("list", resData);
		resMap.put("paging", pagingData);
		return makeResponses(resMap);
	}

	/**
	 * DML쿼리 실행 후 0 이상이면 성공 반환
	 *
	 * @author jklee
	 * @return ResponseVO
	 */
	public ResponseVO resultDml(int returnData){
		if(returnData > 0){
			return makeResponse(null);
		}
		else{
			throw new BusinessException(ExceptionCode.COMMON_SAVE_FAIL);
		}
	}
	
	/**
	 * DML쿼리 실행 후 0 이상이면 성공 반환 & object 리턴
	 *
	 * @author SonKyungmi
	 * @return ResponseVO
	 */
	public ResponseVO resultDml(Map<String, Object> resultMap) {
		if ((Integer)resultMap.get("addCnt") > 0) {
			return makeResponse(resultMap);
		} else {
			throw new BusinessException(ExceptionCode.COMMON_SAVE_FAIL);
		}
	}

	/**
	 * 응답 객체 생성
	 *
	 * @author jklee
	 * @return ResponseVO
	 */
	public ResponseVO makeResponse(Object resData){
		ResponseVO resVO = new ResponseVO(SUCCESS_CODE);
		resVO.getHeader().setResponseMsg(messageSource.getMessage(MESSAGE_PREFIX + resVO.getHeader().getResponseCode(),
				null, LocaleContextHolder.getLocale()));
		resVO.setBody(resData);

		printResponse(resVO);

		return resVO;
	}

	/**
	 * 응답 객체 생성
	 *
	 * @author jklee
	 * @return ResponseVO
	 */
	public ResponseVO makeResponses(Map<String, Object> resData){
		ResponseVO resVO = new ResponseVO(SUCCESS_CODE);
		resVO.getHeader().setResponseMsg(messageSource.getMessage(MESSAGE_PREFIX + resVO.getHeader().getResponseCode(),
				null, LocaleContextHolder.getLocale()));
		resVO.setBody(resData);

		printResponses(resVO, resData);

		return resVO;
	}

	/*==============================================================================
	 * 로그 포맷 관련
	 ==============================================================================*/
	/**
	 * 응답 로그 포맷
	 *
	 * @author jklee
	 */
	private void printResponse(ResponseVO resVO){
		try{
			log.info("##### ========================= [Response value] =========================");
			log.info("[header]");
			log.info("\t{}", objectMapper.writeValueAsString(resVO.getHeader()));
			log.info("[body]");
			log.info("\t{}", objectMapper.writeValueAsString(resVO.getBody()));
			log.info("##### =====================================================================");
		}
		catch(JsonProcessingException e){
			log.error("", e);
		}
	}

	/**
	 * 리스트 응답 로그 포맷
	 *
	 * @author jklee
	 */
	@SuppressWarnings("unchecked")
	private void printResponses(ResponseVO resVO, Map<String, Object> resData){
		try{
			log.info("##### ========================= [Response values] =========================");
			log.info("[header]");
			log.info("\t{}", objectMapper.writeValueAsString(resVO.getHeader()));
			log.info("[body]");
			log.info("\tsize : {}", ((List<Object>) resData.get("list")).size());
			log.debug("\tdata : {}", objectMapper.writeValueAsString(resVO.getBody()));
			log.info("##### =====================================================================");
		}
		catch(JsonProcessingException e){
			log.error("", e);
		}
	}

}
