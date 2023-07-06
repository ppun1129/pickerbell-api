package com.pickerbell.api.user.domain;

import com.pickerbell.api.common.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserInfo extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_number")
	private Long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String userPassword;
	
	private String userNickname;
	
	private String webPushToken;
	
}
