package com.pickerbell.api.location.controller;

import com.pickerbell.api.location.domain.PlaceSearchResult;
import com.pickerbell.api.location.domain.type.FoodStoreType;
import com.pickerbell.api.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 위치 정보와 관련된 API 구현
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/location-api")
public class LocationController {
	
	private final LocationService locationService;
	
	
	/**
	 * 위도 및 경도로 위치 정보를 조회하여 반환
	 * @param longitude 경도
	 * @param latitude 위도
	 * @return 경도 및 위도에 해당하는 장소 정보
	 */
	@GetMapping("/address-info")
	public String getAddressInfoByPoint(@RequestParam String longitude, @RequestParam String latitude) {
		PlaceSearchResult place = locationService.findPlaceByPoint(longitude, latitude);
		
		String resultMessage = "위도 " + latitude + ", 경도" + longitude + "로 조회한 결과 " +
				"해당 지점의 명칭은 " + place.getPlaceName() + "이고," +
				"전체 도로명 주소는 " + place.getRoadAddress() + " 입니다.";
		
		return resultMessage;
	}
	
	/**
	 * 키워드로 장소 검색(그룹 위치 지정 혹은 새 위치로 음식점 추천 받을 경우)
	 * @param keyword 검색어
	 * @return 검색어에 해당하는 장소 목록
	 */
	@GetMapping("/places/keyword")
	public String getPlacesByKeyword(@RequestParam String keyword) {
		List<PlaceSearchResult> locations = locationService.findLocationsBySearchKeyword(keyword);
		
		StringBuilder resultMessage = new StringBuilder();
		
		for (PlaceSearchResult item : locations) {
			resultMessage.append("장소명 : ").append(item.getPlaceName())
					.append(", 도로명 주소 : ").append(item.getRoadAddress())
					.append(", 장소 ID : ").append(item.getPlaceId())
					.append(", 카테고리 : ").append(item.getCategory()).append("\n");
		}
		
		return resultMessage.toString();
	}
	
	/**
	 * 음식점 추천에 필요한 정보를 조합하여 반환
	 * @param foodStoreType 음식점 유형
	 * @param longitude 경도
	 * @param latitude 위도
	 * @param radius 반경(미터 단위)
	 * @return 지정된 조건에 해당하는 랜덤으로 추출된 하나의 음식점 정보, 혹은 조회 결과 없음 메시지
	 */
	@GetMapping("/places/category")
	public String getPlacesByCategory(@RequestParam FoodStoreType foodStoreType, @RequestParam String longitude, @RequestParam String latitude, @RequestParam int radius) {
		List<PlaceSearchResult> stores = locationService.findStoresByCondition(foodStoreType, longitude, latitude, radius);
		
		StringBuilder resultMessage = new StringBuilder();
		
		for (PlaceSearchResult item : stores) {
			resultMessage.append("업체명 : ").append(item.getPlaceName())
					.append(", 도로명 주소 : ").append(item.getRoadAddress())
					.append(", 장소 ID : ").append(item.getPlaceId())
					.append(", 카테고리 : ").append(item.getCategory()).append("\n");
		}
		
		String finalResultMessage;
		
		// 랜덤으로 하나의 업체를 추출하기
		if (stores != null && !stores.isEmpty()) {
			int peekNumber = (int) (Math.random() * 100 % stores.size());
			PlaceSearchResult peekItem = stores.get(peekNumber);
			finalResultMessage = foodStoreType.getFoodStoreTypeName() + " 유형의 음식점으로 이곳을 뽑아봤어요! 한 번 가보실래요?\n" +
					"업체명 : " + peekItem.getPlaceName() + "(" + peekItem.getPlaceId() + ")\n" +
					"주소(도로명) : " + peekItem.getRoadAddress() + "\n" +
					"카테고리 : " + peekItem.getCategory();
		} else {
			finalResultMessage = "이 근처에는 추천할 만한 " + foodStoreType.getFoodStoreTypeName() + " 유형의 음식점이 없네요 :( \n" +
					"다른 유형으로 찾아보시는 건 어떤가요?";
		}
		
		return finalResultMessage;
	}
	
}
