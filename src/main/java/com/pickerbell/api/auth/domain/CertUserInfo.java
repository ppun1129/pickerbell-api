package com.pickerbell.api.auth.domain;

import lombok.Getter;

/**
 * 사용자 인증 처리만을 위해 필요한 정보를 모아둔 클래스.
 * 프론트엔드에서 JWT 발급을 위한 요청을 할 때 전달하는 정보들
 */
@Getter
public class CertUserInfo {
	
	private String rsaMemoryKey;
	private String loginId;
	private String password;
	
}
