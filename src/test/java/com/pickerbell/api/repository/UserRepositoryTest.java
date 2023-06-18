package com.pickerbell.api.repository;

import com.pickerbell.api.group.repository.GroupRepository;
import com.pickerbell.api.group.repository.GroupUserMappingRepository;
import com.pickerbell.api.user.domain.UserInfo;
import com.pickerbell.api.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 관리 기능 테스트 코드
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	GroupUserMappingRepository groupUserMappingRepository;
	
	// 사용자 기본 생성 테스트
	@Test
	@Transactional
	public void userCreateTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("testuser");
		userInfo.setUserPassword("testpw1234@");
		
		long userId = userRepository.save(userInfo);
		
		UserInfo findUserInfo = userRepository.find(userId);
		
		Assertions.assertThat(userInfo).isEqualTo(findUserInfo);
		Assertions.assertThat(userInfo.getUserId()).isEqualTo(findUserInfo.getUserId());
	}
	
	// 사용자를 그룹에 추가하는 테스트 - 추가 확인이 필요하여 코드를 주석처리함
	@Ignore
	@Test
	@Transactional
	public void userJoinToGroupTest() {
//		// 그룹 추가
//		GroupInfo groupInfo = new GroupInfo();
//		groupInfo.setGroupName("Test group");
//		groupInfo.setPreferStoreType(FoodStoreType.FAST_FOOD);
//
//		long groupNumber = groupRepository.save(groupInfo);
//		GroupInfo findGroupInfo = groupRepository.find(groupNumber);
//
//		// 사용자 추가
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUserId("testuser");
//		userInfo.setUserPassword("testpw1234@");
//
//		long userId = userRepository.save(userInfo);
//		UserInfo findUserInfo = userRepository.find(userId);
//
//		// 사용자를 그룹에 가입
//		long mappingId = userRepository.joinToGroup(groupInfo, userInfo);
//		GroupUserMappingInfo groupUserMappingInfo = groupUserMappingRepository.find(mappingId);
//
//		// 매핑 테이블 데이터를 이용하여 그룹 및 사용자 정보 조회
//		Assertions.assertThat(groupInfo).isEqualTo(groupUserMappingInfo.getGroupInfo());
//		Assertions.assertThat(userInfo).isEqualTo(groupUserMappingInfo.getUserInfo());
	}
}
