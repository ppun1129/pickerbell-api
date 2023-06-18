package com.pickerbell.api.location.provider.kakao.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchByPointItemAddress {
	@SerializedName("address_name") private String addressName;
	@SerializedName("region_1depth_name") private String region1DepthName;
	@SerializedName("region_2depth_name") private String region2DepthName;
	@SerializedName("region_3depth_name") private String region3DepthName;
	@SerializedName("mountain_yn") private String mountainYn;
	@SerializedName("main_address_no") private String mainAddressNo;
	@SerializedName("sub_address_no") private String subAddressNo;
}
