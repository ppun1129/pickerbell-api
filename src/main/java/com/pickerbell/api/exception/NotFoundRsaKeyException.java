package com.pickerbell.api.exception;

public class NotFoundRsaKeyException extends RuntimeException {
	
	public NotFoundRsaKeyException() {
		super("유효하지 않은 RSA key 고유 값");
	}
	
}
