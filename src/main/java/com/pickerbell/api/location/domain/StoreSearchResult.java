package com.pickerbell.api.location.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 상점 조회 결과 매핑
 */
@Getter
@Setter
public class StoreSearchResult {
	
	private String storeId;     // 상점고유번호
	private String storeName;   // 상점명
	private String address;     // 주소명
	private String category;    // 유형(카테고리)
	private String latitude;    // 위도
	private String longitude;   // 경도
	private String distance;    // 조회 기준 위치로부터의 거리
	private String telephone;   // 전화번호
	
}
