package com.pickerbell.api.location.domain;

import com.pickerbell.api.common.domain.BaseTimeEntity;
import com.pickerbell.api.group.domain.GroupInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * 상점 추천 이력
 */
@Entity
@Getter
@Setter
public class StoreRecommendHistory extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_number")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "group_number")
	private GroupInfo groupInfo;
	
	@Column(nullable = false)
	private String storeLatitude;
	
	@Column(nullable = false)
	private String storeLongitude;
	
	@Column(nullable = false)
	private String storeName;
	
	@Column(nullable = false)
	private LocalDateTime recommendDatetime;
	
	private String ratingPoint;
	
	@Column(nullable = false)
	private String apiType;
	
	@Column(nullable = false)
	private String storeKey;
	
}
