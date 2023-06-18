package com.pickerbell.api.location.provider.kakao.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

/**
 * Kakao의 로컬 API를 이용하여 '키워드로 장소 검색하기'를 했을 때 배열 내 각 아이템의 결과 값
 */
@Getter
@ToString
public class SearchByKeywordItem {
	@SerializedName("place_name") String placeName;          // 장소명(업체명)
	@SerializedName("address_name") String addressName;        // 전체 지번 주소
	@SerializedName("road_address_name") String roadAddressName;   // 전체 도로명 주소
	@SerializedName("category_name") String categoryName;       // 카테고리 이름
	String id;      // 장소 ID(Kakao에서 관리하는 고유 ID)
	String x;       // X좌표(경도-longitude)
	String y;       // Y좌표(위도-latitude)
}
