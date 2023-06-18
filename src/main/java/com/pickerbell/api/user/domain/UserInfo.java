package com.pickerbell.api.user.domain;

import com.pickerbell.api.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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
