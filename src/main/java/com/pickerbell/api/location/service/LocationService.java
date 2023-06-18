package com.pickerbell.api.location.service;

import com.pickerbell.api.location.domain.PlaceSearchResult;
import com.pickerbell.api.location.domain.type.FoodStoreType;

import java.util.List;

public interface LocationService {
	
	PlaceSearchResult findPlaceByPoint(String longitude, String latitude);
	
	List<PlaceSearchResult> findLocationsBySearchKeyword(String keyword);
	
	List<PlaceSearchResult> findStoresByCondition(FoodStoreType foodStoreType, String longitude, String latitude, int radius);
	
}
