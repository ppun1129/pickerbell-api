package com.pickerbell.api.group.domain;

import com.pickerbell.api.common.domain.BaseTimeEntity;
import com.pickerbell.api.user.domain.converter.MemberStateTypeConverter;
import com.pickerbell.api.user.domain.converter.MemberTypeConverter;
import com.pickerbell.api.user.domain.type.MemberStateType;
import com.pickerbell.api.user.domain.type.MemberType;
import com.pickerbell.api.user.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class GroupUserMappingInfo extends BaseTimeEntity {
	
	public GroupUserMappingInfo() {
	
	}
	public GroupUserMappingInfo(GroupInfo groupInfo, UserInfo userInfo) {
		this.groupInfo = groupInfo;
		this.userInfo = userInfo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_user_mapping_number")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "group_number")
	private GroupInfo groupInfo;
	
	@ManyToOne
	@JoinColumn(name = "member_number")
	private UserInfo userInfo;
	
	@Column(nullable = false)
	@ColumnDefault("'N'")
	@Convert(converter = MemberTypeConverter.class)
	private MemberType memberType;
	
	@Column(nullable = false)
	@ColumnDefault("'W'")
	@Convert(converter = MemberStateTypeConverter.class)
	private MemberStateType memberStateType;
	
	@ColumnDefault("'N'")
	private String messageReceiveYn;
	
}
