package com.pickerbell.api.location.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 위치 정보 제공 서비스에 종속적이지 않은 공통의 위치 정보 저장 객체
 */
@Getter
@Setter
@ToString
public class PlaceSearchResult {
	String placeId;
	String placeName;
	String address;
	String roadAddress;
	String category;
	String pointByX;
	String pointByY;
}
