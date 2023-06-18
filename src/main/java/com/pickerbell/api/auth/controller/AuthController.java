package com.pickerbell.api.auth.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pickerbell.api.auth.domain.CertUserInfo;
import com.pickerbell.api.auth.service.AuthService;
import com.pickerbell.api.security.domain.RsaKey;
import com.pickerbell.api.security.service.RsaKeyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 관련 컨트롤러
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	private final @Qualifier("redisRsaKeyManager") RsaKeyManager rsaKeyManager;
	
	/**
	 * 로그인, 회원가입 등 JWT를 이용하지 않는 API 기능 호출 시 사용할 RSA key 발급
	 * @return RsaKey - 발급된 RSA key
	 */
	@GetMapping
	@RequestMapping("/rsa-key")
	public String generateRsaKey() {
		try {
			Gson gsonForRsaKey = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			RsaKey generatedRsaKey = rsaKeyManager.getGenerateAndSave();
			return gsonForRsaKey.toJson(generatedRsaKey);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * RSA key 객체 중 Public key를 이용하여 암호화 된 파라미터를 가지고 사용자 인증을 수행
	 * @param certUserInfo 발급받은 RSA key의 Public key로 암호화 된 로그인 정보
	 * @return 일반 API 인가에 사용되는 JWT 발급 정보
	 */
	@PostMapping
	@RequestMapping("/cert")
	public String getCert(@RequestBody CertUserInfo certUserInfo) {
		return authService.certUser(certUserInfo);
	}
	
	/**
	 * 테스트용 - redisKey 값을 이용하여 발급된 RSA key 정보 조회
	 * @param memoryKey
	 * @return
	 */
	@GetMapping
	@RequestMapping("/test/rsa-key")
	public String testGetRsaKey(@RequestParam String memoryKey) {
		try {
			Gson rsaKey = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			return rsaKey.toJson(rsaKeyManager.find(memoryKey));
		} catch (Exception e) {
			return null;
		}
	}
	
}
