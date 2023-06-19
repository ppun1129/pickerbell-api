package com.pickerbell.api.auth.service;

import com.pickerbell.api.auth.domain.CertUserInfo;
import com.pickerbell.api.exception.NotFoundRsaKeyException;
import com.pickerbell.api.security.component.JwtOperation;
import com.pickerbell.api.security.component.RsaKeyOperation;
import com.pickerbell.api.security.domain.RsaKey;
import com.pickerbell.api.security.service.RsaKeyManager;
import com.pickerbell.api.user.domain.UserInfo;
import com.pickerbell.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 사용자 인증 처리와 관련된 서비스
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
	
	private final PasswordEncoder passwordEncoder;
	private final @Qualifier("redisRsaKeyManager") RsaKeyManager rsaKeyManager;
	private final RsaKeyOperation rsaKeyOperation;
	private final JwtOperation jwtOperation;
	private final UserRepository userRepository;
	
	
	/**
	 * 로그인 ID와 패스워드를 이용하여 유효한 정보인지 체크 후 JWT 반환.
	 * 로그인 ID와 패스워드는 발급된 개별 RSA key로 암호화 되어 있으며, 요청 시 RSA 객체의 고유 저장 키를 포함해야 함
	 * @param certUserInfo 인증에 필요한 사용자 정보
	 * @return String - 인증 성공 시 인가에 필요한 JWT, 실패 시 실패 문자열
	 */
	@Override
	public String certUser(CertUserInfo certUserInfo) {
		try {
			RsaKey rsaKey = rsaKeyManager.find(certUserInfo.getRsaMemoryKey());
			
			if (rsaKey == null) {
				throw new NotFoundRsaKeyException();
			}
			
			String decryptedLoginId = rsaKeyOperation.decrypt(certUserInfo.getLoginId(), rsaKey.getPrivateKey());
			String decryptedPassword = rsaKeyOperation.decrypt(certUserInfo.getPassword(), rsaKey.getPrivateKey());
			
			UserInfo user = userRepository.findByLoginId(decryptedLoginId);
			
			if (user == null) {
				return "cert info not match";
			}
			
			if (passwordEncoder.matches(decryptedPassword, user.getUserPassword())) {
				// JWT 생성
				return jwtOperation.createUserInfoToken(user);
			} else {
				return "cert info not match";
			}
		} catch (NotFoundRsaKeyException e1) {
			e1.printStackTrace();
			return e1.getMessage();
		} catch (Exception e2) {
			e2.printStackTrace();
			return e2.getMessage();
		}
	}
	
}
