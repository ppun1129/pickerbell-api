package com.pickerbell.api.auth.service;

import com.pickerbell.api.auth.domain.CertUserInfo;

public interface AuthService {
	
	/**
	 * 로그인 ID와 패스워드를 이용하여 유효한 정보인지 체크.
	 * 로그인 ID와 패스워드는 발급된 개별 RSA key로 암호화 되어 있으며, 요청 시 RSA 객체의 고유 저장 키를 포함해야 함
	 * @param certUserInfo 인증에 필요한 사용자 정보
	 * @return String - 결과에 따른 문자열 값(인증 성공 시 인가에 필요한 값을 반환)
	 */
	String certUser(CertUserInfo certUserInfo);
	
}
