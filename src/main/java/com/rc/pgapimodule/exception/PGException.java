package com.rc.pgapimodule.exception;

public class PGException extends RuntimeException {

	private final String errorCode;
	private final String detail;

	public PGException(String errorCode, String message, String detail) {
		super(message);
		this.errorCode = errorCode;
		this.detail = detail;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getDetail() {
		return detail;
	}
}
