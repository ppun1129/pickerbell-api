package com.pickerbell.api.user.service;

import com.google.gson.JsonObject;
import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.user.domain.UserInfo;

import java.util.List;

public interface UserService {
	
	/**
	 * 사용자의 고유 순번 값 기준으로 정보 조회
	 * @param userNumber 사용자 고유 순번
	 * @return UserInfo
	 */
	UserInfo getUserInfo(long userNumber);
	
	/**
	 * 사용자 로그인 ID 기준으로 정보 조회
	 * @param loginId 로그인 ID
	 * @return UserInfo
	 */
	UserInfo findByLoginId(String loginId);
	
	/**
	 * 사용자 정보 저장
	 * @param userInfo DB에 저장할 사용자 정보
	 * @return 저장된 사용자의 고유 순번
	 */
	long setUserInfo(UserInfo userInfo);
	
	// 사용자가 가입된 그룹 조회
	
	/**
	 * 사용자가 가입된 그룹들을 조회
	 * @param userNumber 사용자의 고유 순번
	 * @return List<GroupInfo> 사용자가 가입된 그룹 목록
	 */
	List<GroupInfo> getUserJoinedGroups(long userNumber);
	
	/**
	 * 타입별 사용자 정보 중복 여부 조회
	 * @param type 체크 유형(로그인 ID, 닉네임)
	 * @param keyword 유형에 대한 값
	 * @return 중복 여부 조회 결과
	 */
	JsonObject checkDuplicationKeyword(String type, String keyword);
	
}
