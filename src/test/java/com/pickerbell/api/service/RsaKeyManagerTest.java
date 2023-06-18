package com.pickerbell.api.service;

import com.pickerbell.api.security.domain.RsaKey;
import com.pickerbell.api.security.component.RsaKeyOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * RSA 관련 테스트 코드 작성
 * 지금은 testRsaKey라는 메서드에서 모든 과정을 테스트하고 있지만, 단계별로 테스트 코드 분리를 목표로 함
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RsaKeyManagerTest {
	
	@Autowired
	RsaKeyOperation rsaKeyOperation;
	
	@Test
	public void testRsaKey() {
		// 원본 문자열
		String originalString = "It's original string~!!";
		
		// RSA key 발급
		RsaKey rsaKey = null;
		try {
			rsaKey = rsaKeyOperation.generateRsaKey();
		} catch (Exception e) {
			// Exception 발생 시 어떻게 판단할 것인가? -> 키 발급이 이루어지지 않아 null인 경우를 체크하므로 catch 절에서는 별도 처리 않음.
		}
		
		assertThat(rsaKey).isNotNull();
		
		// 문자열을 발급받은 Public Key로 encode
		String encryptString = null;
		try {
			encryptString = rsaKeyOperation.encrypt(originalString, rsaKey.getPublicKey());
		} catch (Exception e) {
			// RSA key 발급 시와 동일한 기준으로 별도 처리하지 않음
		}
		
		assertThat(encryptString).isNotNull();
		
		// 암호화 된 문자열을 발급받은 Private Key로 decode
		String decryptString = null;
		try {
			decryptString = rsaKeyOperation.decrypt(encryptString, rsaKey.getPrivateKey());
		} catch (Exception e) {
			// 위 catch 절들과 동일
		}
		
		assertThat(decryptString).isNotNull();
		
		// 원본 문자열과 decode된 문자열의 동일 여부 확인
		assertThat(originalString).isEqualTo(decryptString);
	}
	
}