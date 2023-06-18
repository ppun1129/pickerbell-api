package com.pickerbell.api.group.service;

import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.group.domain.GroupUserMappingInfo;
import com.pickerbell.api.group.repository.GroupRepository;
import com.pickerbell.api.group.repository.GroupUserMappingRepository;
import com.pickerbell.api.user.domain.UserInfo;
import com.pickerbell.api.user.domain.type.MemberStateType;
import com.pickerbell.api.user.domain.type.MemberType;
import com.pickerbell.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GroupUserMappingServiceImpl implements GroupUserMappingService {
	
	private final GroupUserMappingRepository groupUserMappingRepository;
	private final GroupRepository groupRepository;
	private final UserRepository userRepository;
	
	
	@Transactional
	@Override
	public long save(long groupNumber, long userNumber) {
		GroupInfo groupInfo = groupRepository.find(groupNumber);
		UserInfo userInfo = userRepository.find(userNumber);
		GroupUserMappingInfo groupUserMappingInfo = new GroupUserMappingInfo(groupInfo, userInfo);
		groupUserMappingInfo.setMemberType(MemberType.NORMAL);          // 일반 멤버
		groupUserMappingInfo.setMemberStateType(MemberStateType.WAIT);  // 최초 가입 대기 상태
		
		groupUserMappingRepository.save(groupUserMappingInfo);
		return groupUserMappingInfo.getId();
	}
}
