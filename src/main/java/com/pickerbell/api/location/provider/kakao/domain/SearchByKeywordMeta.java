package com.pickerbell.api.location.provider.kakao.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

/**
 * Kakao의 로컬 API를 이용하여 '키워드로 장소 검색하기'를 했을 때 meta 값
 */
@Getter
@ToString
public class SearchByKeywordMeta {
	@SerializedName("pageable_count") private int pageableCount;        // 전체 결과 수 중 노출 가능한 데이터 수
	@SerializedName("total_count") private int totalCount;      // 전체 결과 수
	@SerializedName("is_end") private boolean isEnd;        // 마지막 페이지 여부
}
