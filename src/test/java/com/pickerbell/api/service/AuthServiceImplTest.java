package com.pickerbell.api.service;

import com.pickerbell.api.auth.service.AuthService;
import com.pickerbell.api.user.domain.UserInfo;
import com.pickerbell.api.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 사용자 인증 처리와 관련된 서비스를 테스트 하는 코드
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceImplTest {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void passwordCompareTest() {
		// DB에 저장할 사용자 정보
		UserInfo insertUser = new UserInfo();
		insertUser.setUserId("testuser1");
		insertUser.setUserPassword("user_pass_123!@#");
		userService.setUserInfo(insertUser);
		
		// 인코딩 전후 패스워드 비교 요청
		assertThat(passwordEncoder.matches("user_pass_123!@#", userService.findByLoginId("testuser1").getUserPassword()))
				.isTrue();
	}
	
}