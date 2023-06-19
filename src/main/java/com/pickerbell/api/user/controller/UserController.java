package com.pickerbell.api.user.controller;

import com.google.gson.JsonObject;
import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.user.domain.UserInfo;
import com.pickerbell.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 사용자 정보 조회/조작과 관련된 API 구현
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	
	// 사용자 정보 조회
	@GetMapping("/{userNumber}")
	public UserInfo getUserInfo(@PathVariable("userNumber") long userNumber) {
		return userService.getUserInfo(userNumber).get();
	}
	
	// 사용자 정보 저장
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public UserInfo setUserInfo(@RequestBody UserInfo userInfo) {
		userService.setUserInfo(userInfo);
		return userInfo;
	}
	
	// 사용자가 가입한 그룹 목록 조회
	@GetMapping("/{userNumber}/groups")
	public List<GroupInfo> getUserJoinGroups(@PathVariable("userNumber") long userNumber) {
		return userService.getUserJoinedGroups(userNumber);
	}
	
	// 타입별 사용자 정보 중복 여부 조회
	@GetMapping("/duplication-check")
	public JsonObject checkDuplicationKeyword(@RequestParam String type, @RequestParam String keyword) {
		return userService.checkDuplicationKeyword(type, keyword);
	}
	
}
