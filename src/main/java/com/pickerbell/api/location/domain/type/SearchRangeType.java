package com.pickerbell.api.location.domain.type;

/**
 * 추천 범위 정의
 */
public enum SearchRangeType {
	THREE_HUNDRED_METER(300),
	FIVE_HUNDRED_METER(500),
	ONE_THOUSAND_KILO_METER(1000),
	TWO_THOUSAND_KILO_METER(2000);
	
	private final long range;
	
	SearchRangeType(long range) {
		this.range = range;
	}
}
