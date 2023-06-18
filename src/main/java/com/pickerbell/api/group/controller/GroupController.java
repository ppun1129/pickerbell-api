package com.pickerbell.api.group.controller;

import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.group.service.GroupService;
import com.pickerbell.api.group.service.GroupUserMappingService;
import com.pickerbell.api.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 그룹 정보 조회/조작과 관련된 API 구현
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
public class GroupController {
	
	private final GroupService groupService;
	private final GroupUserMappingService groupUserMappingService;
	
	
	// 그룹 정보 조회
	@GetMapping("/{groupNumber}")
	public GroupInfo getGroupInfo(@PathVariable("groupNumber") long groupNumber) {
		return groupService.getGroupInfo(groupNumber);
	}
	
	// 그룹 정보 저장
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public GroupInfo setGroupInfo(@RequestBody GroupInfo groupInfo) {
		groupService.setGroupInfo(groupInfo);
		return groupInfo;
	}
	
	// 사용자를 그룹에 가입 처리
	@PostMapping("/{groupNumber}/user/{userNumber}")
	public long joinUser(@PathVariable("groupNumber") long groupNumber,
	                     @PathVariable("userNumber") long userNumber) {
		return groupUserMappingService.save(groupNumber, userNumber);
	}
	
	// 그룹에 가입된 사용자 목록 조회
	@GetMapping("/{groupNumber}/users")
	public List<UserInfo> getGroupJoinUsers(@PathVariable("groupNumber") long groupNumber) {
		return groupService.getGroupJoinedUsers(groupNumber);
	}
	
}
