package com.pickerbell.api.location.domain.type;

/**
 * 음식점 유형 코드 정의
 * 선호 카테고리 정보로도 사용됨
 */
public enum FoodStoreType {
	KOREAN_FOOD("KFD", "한식"),
	JAPANESE_FOOD("JFD", "일식"),
	CHINESE_FOOD("CFD", "중식"),
	FAST_FOOD("FFD", "패스트푸드"),
	SNACK("SNK", "분식");
	
	private final String code;
	private final String name;
	
	FoodStoreType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getFoodStoreTypeCode() {
		return this.code;
	}
	public String getFoodStoreTypeName() {
		return this.name;
	}
	
	public static FoodStoreType getFoodStoreTypeByCode(String code) {
		for (FoodStoreType foodStoreType : FoodStoreType.values()) {
			if (foodStoreType.getFoodStoreTypeCode().equals(code)) {
				return foodStoreType;
			}
		}
		return null;
	}
}
