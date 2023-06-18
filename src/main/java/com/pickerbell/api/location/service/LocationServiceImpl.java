package com.pickerbell.api.location.service;

import com.pickerbell.api.location.domain.PlaceSearchResult;
import com.pickerbell.api.location.domain.type.FoodStoreType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 위치 정보 제공자를 활용한 위치 정보 조회 서비스 구현체
 */
@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
	
	private final LocationProvideService locationProvideService;
	
	
	@Override
	public PlaceSearchResult findPlaceByPoint(String longitude, String latitude) {
		return locationProvideService.findPlaceByPoint(longitude, latitude);
	}
	
	@Override
	public List<PlaceSearchResult> findLocationsBySearchKeyword(String keyword) {
		return locationProvideService.findLocationsBySearchKeyword(keyword);
	}
	
	@Override
	public List<PlaceSearchResult> findStoresByCondition(FoodStoreType foodStoreType, String longitude, String latitude, int radius) {
		return locationProvideService.findStoresByConditions(foodStoreType, longitude, latitude, radius);
	}
}
