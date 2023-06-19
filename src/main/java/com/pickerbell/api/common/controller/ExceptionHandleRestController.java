package com.pickerbell.api.common.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * API 호출 중 exception 발생 시 이 컨트롤러에서 핸들링 함. 오류 메시지 포맷을 정하여 일관적인 형태로 나타나도록 해야 함
 */
@RestControllerAdvice
public class ExceptionHandleRestController {
	
	/**
	 * NoSuchElementException - 찾는 데이터가 없는 경우(Optional)
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public String noSuchElementExceptionHandler(Exception e) {
		return e.getMessage();
	}
	
}
