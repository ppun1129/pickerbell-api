package com.pickerbell.api.user.domain.converter;

import com.pickerbell.api.user.domain.type.MemberStateType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * MemberStateType을 엔티티 혹은 DB에 저장할 때 값을 변환
 * 엔티티에는 MemberStateType enum, DB에는 enum에 해당하는 한 글자 code를 저장
 */
@Converter
public class MemberStateTypeConverter implements AttributeConverter<MemberStateType, String> {
	
	@Override
	public String convertToDatabaseColumn(MemberStateType memberStateType) {
		return memberStateType.getMemberStateTypeCode();
	}
	
	@Override
	public MemberStateType convertToEntityAttribute(String memberStateTypeCode) {
		return MemberStateType.getMemberStateTypeByCode(memberStateTypeCode);
	}
}
