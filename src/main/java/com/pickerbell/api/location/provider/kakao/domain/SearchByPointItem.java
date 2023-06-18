package com.pickerbell.api.location.provider.kakao.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

/**
 * Kakao의 로컬 API를 이용하여 '좌표로 주소 변환하기'를 했을 때 root 결과 값
 */
@Getter
@ToString
public class SearchByPointItem {
	@SerializedName("road_address") private SearchByPointItemRoadAddress roadAddress;
	private SearchByPointItemAddress address;
}
