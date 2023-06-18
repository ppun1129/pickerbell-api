package com.pickerbell.api.user.domain.converter;

import com.pickerbell.api.user.domain.type.MemberType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * MemberType을 엔티티 혹은 DB에 저장할 때 값을 변환
 * 엔티티에는 MemberType enum, DB에는 enum에 해당하는 한 글자 code를 저장
 */
@Converter
public class MemberTypeConverter implements AttributeConverter<MemberType, String> {
	
	@Override
	public String convertToDatabaseColumn(MemberType memberType) {
		return memberType.getMemberTypeCode();
	}
	
	@Override
	public MemberType convertToEntityAttribute(String memberTypeCode) {
		return MemberType.getMemberTypeByCode(memberTypeCode);
	}
}
