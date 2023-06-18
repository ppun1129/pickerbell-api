package com.pickerbell.api.group.service;

import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.user.domain.UserInfo;

import java.util.List;

public interface GroupService {
	
	// 특정 그룹 정보 반환
	GroupInfo getGroupInfo(long groupNumber);
	
	// 그룹 정보 저장
	long setGroupInfo(GroupInfo groupInfo);
	
	// 그룹에 가입된 사용자 목록 조회
	List<UserInfo> getGroupJoinedUsers(long groupNumber);
}
