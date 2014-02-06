package com.srswitch.exception;

public class BusinessException extends Exception{

	private static final long serialVersionUID = -3375707706502432693L;
	
	private String exceptionCode;
	private String exceptionMessage;
	
	public BusinessException(String exceptionCode) {
		super(exceptionCode);
	    this.setExceptionCode(exceptionCode);
	}

	public BusinessException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}
	
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}
}
