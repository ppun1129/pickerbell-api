package com.pickerbell.api.group.service;

import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.group.repository.GroupRepository;
import com.pickerbell.api.group.repository.GroupUserMappingRepository;
import com.pickerbell.api.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
	
	private final GroupRepository groupRepository;
	private final GroupUserMappingRepository groupUserMappingRepository;
	
	
	// 그룹 정보 조회
	@Override
	public GroupInfo getGroupInfo(long groupNumber) {
		return groupRepository.find(groupNumber);
	}
	
	// 그룹 정보 저장
	@Transactional
	@Override
	public long setGroupInfo(GroupInfo groupInfo) {
		return groupRepository.save(groupInfo);
	}
	
	// 그룹에 가입된 사용자 목록 조회
	@Override
	public List<UserInfo> getGroupJoinedUsers(long groupNumber) {
		return groupUserMappingRepository.usersByGroupNumber(groupNumber);
	}
}
