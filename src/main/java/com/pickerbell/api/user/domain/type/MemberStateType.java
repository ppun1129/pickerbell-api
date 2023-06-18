package com.pickerbell.api.user.domain.type;

/**
 * 멤버의 특정 그룹 내 상태 코드 정의
 */
public enum MemberStateType {
	WAIT("W"),      // 가입 대기
	ACTIVE("A"),    // 활동 중
	DELETE("D");    // 탈퇴
	
	private final String code;
	
	MemberStateType(String code) {
		this.code = code;
	}
	
	public String getMemberStateTypeCode() {
		return this.code;
	}
	
	public static MemberStateType getMemberStateTypeByCode(String code) {
		for (MemberStateType memberStateType : MemberStateType.values()) {
			if (memberStateType.getMemberStateTypeCode().equals(code)) {
				return memberStateType;
			}
		}
		return null;
	}
}
