package com.pickerbell.api.location.domain.converter;

import com.pickerbell.api.location.domain.type.FoodStoreType;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * FoodStoreType을 엔티티 혹은 DB에 저장할 때 값을 변환
 * 엔티티에는 FoodStoreType enum, DB에는 enum에 해당하는 한 글자 code를 저장
 */
@Convert
public class FoodStoreTypeConverter implements AttributeConverter<FoodStoreType, String> {
	
	@Override
	public String convertToDatabaseColumn(FoodStoreType foodStoreType) {
		return foodStoreType == null ? "" : foodStoreType.getFoodStoreTypeCode();
	}
	
	@Override
	public FoodStoreType convertToEntityAttribute(String foodStoreTypeCode) {
		return FoodStoreType.getFoodStoreTypeByCode(foodStoreTypeCode);
	}
}
