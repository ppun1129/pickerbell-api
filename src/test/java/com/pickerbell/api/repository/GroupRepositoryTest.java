package com.pickerbell.api.repository;

import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.group.repository.GroupRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

// 그룹 관리 기능 테스트
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTest {
	
	@Autowired
	private GroupRepository groupRepository;
	
	// 그룹 생성 테스트
	@Test
	@Transactional
	public void groupCreateTest() {
		GroupInfo groupInfo = new GroupInfo();
		groupInfo.setGroupName("Test group");
		
		long groupNumber = groupRepository.save(groupInfo);
		GroupInfo findGroupInfo = groupRepository.find(groupNumber);
		
		Assertions.assertThat(groupInfo).isEqualTo(findGroupInfo);
	}
	
}