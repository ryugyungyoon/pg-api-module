package com.rc.pgapimodule.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = 1614542236477807379L;
	private final ExceptionCode errorCode;
	private final Object data;

	public BusinessException(String message, ExceptionCode errorCode){
		super(message);
		this.errorCode = errorCode;
		this.data = null;
	}

	public BusinessException(ExceptionCode errorCode){
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.data = null;
	}

	public BusinessException(ExceptionCode errorCode, Object data){
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.data = data;
	}

	public ExceptionCode getErrorCode(){
		return errorCode;
	}

	public Object getData(){
		return data;
	}

}
