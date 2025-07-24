package com.rc.pgapimodule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionCode {

	SUCCESS(200, "2001", "OK"),

	//Common
	COMMON_INVALID_INPUT_VALUE(400, "C001", " 잘못된 입력 값입니다."),
	COMMON_INVALID_TYPE_VALUE(400, "C002", " 잘못된 타입입니다."),
	COMMON_SAVE_FAIL(400, "C003", "저장에 실패 하였습니다."),
	COMMON_FILE_UPLOAD_FAIL(400, "C004", "File Upload Fail."),
	COMMON_JSON_PARSING_FAIL(400, "C005","JSON Parings Failed"),
	COMMON_REQUEST_CONNECTION(400, "C005","커넥션 실패"),

	// parameter validation
	EXCEPTION_PARAM_NULL(400, "4001", "필수값이 누락."),
	EXCEPTION_PARAM_SIZE(400, "4002", "허용 길이 오류."),
	EXCEPTION_PARAM_MAX(400, "4003", "최대 길이 초과."),
	EXCEPTION_PARAM_MIN(400, "4004", "최소 길이 미달."),
	EXCEPTION_PARAM_FORMAT(400, "4005", "형식 오류."),
	EXCEPTION_PARAM_UNKNOWN(400, "4099", "알 수 없는 파라메터 검증."),

	EXCEPTION_HEADERS_PARAM_NULL(403, "4031", "필수값이 누락."),
	EXCEPTION_HEADERS_PARAM_PARTNER_CODE_NULL(403, "4032", "필수값이 누락."),
	EXCEPTION_HEADERS_PARAM_SECRET_KEY_NULL(403, "4033", "필수값이 누락."),
	EXCEPTION_HEADERS_PARAM_TRASACTION_NO_NULL(403, "4034", "필수값이 누락."),

	// proc
	EXCEPTION_PROC_NOT_FOUND_DATA(500, "5001", "일치하는 데이터 없음."),
	EXCEPTION_PROC_VALID_DATA(500, "5002", "데이터 검증 실패."),
	EXCEPTION_PROC_MAKE_DATA(500, "5003", "데이터 생성 오류."),
	EXCEPTION_PROC_TRANS_DATA(500, "5004", "Value Object 변환 오류."),
	EXCEPTION_PROC_ALREADY(500, "5005", "처리 중복 오류."),
	EXCEPTION_PROC_INSERT_DATA(500, "5101", "등록 실패."),
	EXCEPTION_PROC_UPDATE_DATA(500, "5102", "수정 실패."),
	EXCEPTION_PROC_VALID_BALANCE(500, "52001", "예치금 검증 실패."),

	// server
	EXCEPTION_SERVER_SQL(500, "5901", "DB 사용 오류."),
	EXCEPTION_SERVER_FILE(500, "5902", "File 처리 오류."),
	EXCEPTION_SERVER_CONNECT(500, "5903", "서버 연결 오류."),

	//etc
	EXCEPTION_UNKNOWN(500, "9999", "알 수 없는 오류."),
	;

	@Getter
	private int status;

	@Getter
	private String errorCode;

	@Getter
	private String message;

}