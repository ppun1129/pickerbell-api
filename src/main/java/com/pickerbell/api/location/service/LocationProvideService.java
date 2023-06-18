package com.pickerbell.api.location.service;

import com.pickerbell.api.location.domain.PlaceSearchResult;
import com.pickerbell.api.location.domain.type.FoodStoreType;

import java.util.List;

/**
 * 위치정보와 관련된 처리를 담당하는 서비스의 인터페이스
 * 위치와 관련된 정보를 제공하는 서비스(네이버 지도, 카카오맵, 티맵 등)별로 이 인터페이스를 구현하여 사용하며
 * 하나의 서비스로 구현이 충족될 경우 다른 서비스를 추가로 구현할 필요는 없음
 */
public interface LocationProvideService {
	
	/*
	검색어로 위치 목록 조회
	위도/경도로 위치 정보 조회(현재 위치 기준으로 조회할 경우)
	 */
	
	// 위도/경도로 현재 위치에 대한 정보(주소) 조회
	PlaceSearchResult findPlaceByPoint(String longitude, String latitude);
	
	// 검색어로 위치 목록 조회
	List<PlaceSearchResult> findLocationsBySearchKeyword(String keyword);
	
	// 추천 업체 선정을 위한 원천 데이터 조회
	List<PlaceSearchResult> findStoresByConditions(FoodStoreType foodStoreType, String longitude, String latitude, int radius);
	
}
