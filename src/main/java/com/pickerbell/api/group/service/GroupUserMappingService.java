package com.pickerbell.api.group.service;

public interface GroupUserMappingService {
	
	// 사용자를 그룹에 가입
	long save(long groupNumber, long userNumber);
	
}
