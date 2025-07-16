package com.rc.pgapimodule.exception;

import com.rc.pgapimodule.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class PGExceptionHandler {

	@ExceptionHandler(PGException.class)
	public ResponseEntity<CommonResponse<Void>> handlePgException(PGException ex) {
		log.error("[PG ERROR] {} - {}", ex.getErrorCode(), ex.getDetail());
		return ResponseEntity.ok(CommonResponse.fail(ex.getErrorCode(), ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponse<Void>> handleGeneric(Exception ex) {
		log.error("[SYSTEM ERROR] {}", ex.getMessage(), ex);
		return ResponseEntity.ok(CommonResponse.fail("SYS500", "시스템 오류가 발생했습니다."));
	}
}
