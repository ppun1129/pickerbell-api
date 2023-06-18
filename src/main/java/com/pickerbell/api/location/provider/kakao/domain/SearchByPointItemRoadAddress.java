package com.pickerbell.api.location.provider.kakao.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchByPointItemRoadAddress {
	@SerializedName("address_name") private String addressName;
	@SerializedName("region_1depth_name") private String region1DepthName;
	@SerializedName("region_2depth_name") private String region2DepthName;
	@SerializedName("region_3depth_name") private String region3DepthName;
	@SerializedName("road_name") private String roadName;
	@SerializedName("main_building_no") private String mainBuildingNo;
	@SerializedName("sub_building_no") private String subBuildingNo;
	@SerializedName("building_name") private String buildingName;
	@SerializedName("zone_no") private String zoneNo;
}
