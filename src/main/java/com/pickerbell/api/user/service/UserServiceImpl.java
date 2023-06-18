package com.pickerbell.api.user.service;

import com.google.gson.JsonObject;
import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.group.repository.GroupUserMappingRepository;
import com.pickerbell.api.user.domain.UserInfo;
import com.pickerbell.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사용자 정보 처리 기능에 대한 서비스 로직 구현체
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final GroupUserMappingRepository groupUserMappingRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	/**
	 * 사용자의 고유 순번 값 기준으로 정보 조회
	 * @param userNumber 사용자 고유 순번
	 * @return UserInfo
	 */
	@Override
	public UserInfo getUserInfo(long userNumber) {
		return userRepository.find(userNumber);
	}
	
	/**
	 * 사용자 로그인 ID 기준으로 정보 조회
	 * @param loginId 로그인 ID
	 * @return UserInfo
	 */
	@Override
	public UserInfo findByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	// 사용자 정보 저장
	@Transactional
	@Override
	public long setUserInfo(UserInfo userInfo) {
		// 암호화 되어있는 일부 정보를 복호화
		
		userInfo.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
		userRepository.save(userInfo);
		return userInfo.getId();
	}
	
	// 사용자가 가입된 그룹 목록 조회
	@Override
	public List<GroupInfo> getUserJoinedGroups(long userNumber) {
		return groupUserMappingRepository.groupsByUserNumber(userNumber);
	}
	
	// 타입별 사용자 정보 중복 여부 조회
	@Override
	public JsonObject checkDuplicationKeyword(String type, String keyword) {
		JsonObject result = new JsonObject();
		
		if ("userid".equalsIgnoreCase(type)) {
			result.addProperty("result", userRepository.isExistUserId(keyword));
		} else if ("nickname".equalsIgnoreCase(type)) {
			result.addProperty("result", userRepository.isExistNickname(keyword));
		}
		
		return result;
	}
}
