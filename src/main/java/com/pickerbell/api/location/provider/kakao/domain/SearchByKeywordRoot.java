package com.pickerbell.api.location.provider.kakao.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Kakao의 로컬 API를 이용하여 '키워드로 장소 검색하기'를 했을 때 root 결과 값
 */
@Getter
@ToString
public class SearchByKeywordRoot {
	private List<SearchByKeywordItem> documents;
	private SearchByKeywordMeta meta;
}
