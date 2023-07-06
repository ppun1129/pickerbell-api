package com.pickerbell.api.group.domain;

import com.pickerbell.api.common.domain.BaseTimeEntity;
import com.pickerbell.api.location.domain.converter.FoodStoreTypeConverter;
import com.pickerbell.api.location.domain.type.FoodStoreType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
