package com.pickerbell.api.group.domain;

import com.pickerbell.api.common.domain.BaseTimeEntity;
import com.pickerbell.api.location.domain.converter.FoodStoreTypeConverter;
import com.pickerbell.api.location.domain.type.FoodStoreType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class GroupInfo extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_number")
	private Long id;
	
	@Column(nullable = false)
	private String groupName;
	
	private String groupDescription;
	
	private String locationLatitude;
	
	private String locationLongitude;
	
	private Integer storeFindRange;
	
	@Convert(converter = FoodStoreTypeConverter.class)
	private FoodStoreType preferStoreType;
	
	@ColumnDefault("'N'")
	private String recommendAutoYn;
	
	private LocalDateTime recommendTime;
	
	@ColumnDefault("0")
	private Integer recommendNoDuplicateInterval;
	
}
