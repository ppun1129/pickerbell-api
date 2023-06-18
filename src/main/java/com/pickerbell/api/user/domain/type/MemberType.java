package com.pickerbell.api.user.domain.type;

/**
 * 멤버 유형 코드 정의
 */
public enum MemberType {
	MASTER("M"),
	NORMAL("N");
	
	private final String code;
	
	MemberType(String code) {
		this.code = code;
	}
	
	public String getMemberTypeCode() {
		return this.code;
	}
	
	public static MemberType getMemberTypeByCode(String code) {
		for (MemberType memberType : MemberType.values()) {
			if (memberType.getMemberTypeCode().equals(code)) {
				return memberType;
			}
		}
		return null;
	}
}
