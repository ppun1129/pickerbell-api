package com.pickerbell.api.location.provider.kakao.service;

import com.google.gson.Gson;
import com.pickerbell.api.location.domain.PlaceSearchResult;
import com.pickerbell.api.location.domain.type.FoodStoreType;
import com.pickerbell.api.location.provider.kakao.domain.SearchByKeywordItem;
import com.pickerbell.api.location.provider.kakao.domain.SearchByKeywordRoot;
import com.pickerbell.api.location.provider.kakao.domain.SearchByPointItem;
import com.pickerbell.api.location.provider.kakao.domain.SearchByPointRoot;
import com.pickerbell.api.location.service.LocationProvideService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 카카오 API를 이용한 위치 정보 서비스 인터페이스(LocationProvideService)의 구현체
 * 카카오 API를 호출하여 조회된 데이터들은 최종적으로 Pickerbell에서 위치 관련 데이터 조회 결과를 담는 공통된 정보 객체인 PlaceSearchResult 객체로 변환하여 반환함.
 */
@Service
public class KakaoLocationService implements LocationProvideService {
	
	private static WebClient kakaoClient;
	
	private static final String KAKAO_BASE_URL = "https://dapi.kakao.com/";
	
	private static final String KAKAO_URL_SEARCH_PLACE_POINT = "/v2/local/geo/coord2address.json";
	private static final String KAKAO_URL_SEARCH_PLACES_KEYWORD = "/v2/local/search/keyword.json";
	
	@Value("${kakao.api.key}")
	private String kakaoApiKey;
	
	@PostConstruct
	private void init() {
		kakaoClient = WebClient.builder()
				.baseUrl(KAKAO_BASE_URL)
				.defaultHeader(HttpHeaders.AUTHORIZATION, String.format("KakaoAK %s", kakaoApiKey))
				.build();
	}
	
	/**
	 * 위도와 경도로 장소명 및 주소 정보 반환
	 * @param longitude 경도
	 * @param latitude 위도
	 * @return PlaceSearchResult - 경도 및 위도에 해당하는 특정 지점에 대한 정보
	 */
	@Override
	public PlaceSearchResult findPlaceByPoint(String longitude, String latitude) {
		SearchByPointItem result = callApiFindPlaceByPoint(longitude, latitude);
		return convertResultPointApiToCommon(result);
	}
	
	/**
	 * 키워드로 장소 검색(기준점 필요 없음)
	 * @param keyword 검색어
	 * @return PlaceSearchResult list -> 검색어에 해당하는 위치 정보 리스트
	 */
	public List<PlaceSearchResult> findLocationsBySearchKeyword(String keyword) {
		List<PlaceSearchResult> results = new ArrayList<>();
		
		int page = 1;
		
		while(true) {
			SearchByKeywordRoot apiResultInLoop = callApiFindLocationsBySearchKeyword(keyword, page++);
			results.addAll(
					apiResultInLoop.getDocuments()
							.stream()
							.map(data -> convertResultKeywordApiToCommon(data))
							.collect(Collectors.toList())
			);
			
			if (apiResultInLoop.getMeta().isEnd()) {
				break;
			}
		}
		
		return results;
	}
	
	/**
	 * 추천 업체 선정을 위한 원천 데이터 조회
	 * @param foodStoreType 음식점 유형
	 * @param longitude 경도
	 * @param latitude 위도
	 * @param radius 반경(미터 단위)
	 * @return PlaceSearchResult list -> 지정된 조건에 해당하는 업체 정보 리스트
	 */
	@Override
	public List<PlaceSearchResult> findStoresByConditions(FoodStoreType foodStoreType, String longitude, String latitude, int radius) {
		List<PlaceSearchResult> results = new ArrayList<>();
		
		int page = 1;
		
		while(true) {
			SearchByKeywordRoot apiResultInLoop = callApiFindStoresByConditions(foodStoreType, longitude, latitude, radius, page++);
			results.addAll(
					apiResultInLoop.getDocuments()
							.stream()
							.map(data -> convertResultKeywordApiToCommon(data))
							.collect(Collectors.toList())
			);
			
			if (apiResultInLoop.getMeta().isEnd()) {
				break;
			}
		}
		
		return results;
	}
	
	
	/* 카카오 API 호출 코드 */
	/**
	 * 좌표로 주소 변환하기 카카오 API 호출. 현재 위치 확인 시 사용되므로 최상위 결과 하나만 반환하도록 함
	 * @param longitude 경도
	 * @param latitude 위도
	 * @return SearchByPointItem - 좌표로 주소 변환하기 카카오 API 호출 결과. 결과가 여러 건일 경우 첫 번째 결과 값
	 */
	public SearchByPointItem callApiFindPlaceByPoint(String longitude, String latitude) {
		String resultString = kakaoClient.get()
				.uri(uriBuilder -> uriBuilder.path(KAKAO_URL_SEARCH_PLACE_POINT)
						.queryParam("x", longitude)
						.queryParam("y", latitude)
						.queryParam("input_coord", "WGS84")
						.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		Gson gson = new Gson();
		SearchByPointRoot root = gson.fromJson(resultString, SearchByPointRoot.class);
		
		return root.getDocuments().size() > 0 ? root.getDocuments().get(0) : null;
	}
	
	/**
	 * 키워드로 장소 검색하기 카카오 API 호출. 기준점(위도 및 경도) 정보 없이 검색어에 대한 결과를 반환
	 * @param keyword 검색어
	 * @param page 데이터 분할 조회 시 사용할 페이지 번호
	 * @return SearchByKeywordRoot - 키워드로 장소 검색하기 카카오 API 호출 결과
	 */
	public SearchByKeywordRoot callApiFindLocationsBySearchKeyword(String keyword, int page) {
		String resultString = kakaoClient.get()
				.uri(uriBuilder -> uriBuilder.path(KAKAO_URL_SEARCH_PLACES_KEYWORD)
						.queryParam("query", keyword)
						.queryParam("page", page)
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		Gson gson = new Gson();
		SearchByKeywordRoot root = gson.fromJson(resultString, SearchByKeywordRoot.class);
		
		return root;
	}
	
	/**
	 * 키워드로 장소 검색하기 카카오 API 호출. 위도 및 경도, 반경 정보를 이용하며 음식점 유형을 키워드로 지정하여 조회된 데이터를 반환
	 * @param foodStoreType 음식점 유형(FoodStoreType)
	 * @param longitude 경도
	 * @param latitude 위도
	 * @param radius 반경(미터 단위)
	 * @param page 데이터 분할 조회 시 사용할 페이지 번호
	 * @return
	 */
	public SearchByKeywordRoot callApiFindStoresByConditions(FoodStoreType foodStoreType, String longitude, String latitude, int radius, int page) {
		String resultString = kakaoClient.get()
				.uri(uriBuilder -> uriBuilder.path(KAKAO_URL_SEARCH_PLACES_KEYWORD)
						.queryParam("query", foodStoreType.getFoodStoreTypeName())
						.queryParam("x", longitude)
						.queryParam("y", latitude)
						.queryParam("radius", radius)
						.queryParam("page", page)
						.build())
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		Gson gson = new Gson();
		SearchByKeywordRoot root = gson.fromJson(resultString, SearchByKeywordRoot.class);
		
		return root;
	}
	
	
	/* 카카오 API 호출 결과를 Pickerbell 내부 사용 객체로 변환하는 코드 */
	/**
	 * 위도/경도로 조회한 위치 정보 결과를 공통 객체로 반환
	 * @param item
	 * @return
	 */
	private PlaceSearchResult convertResultPointApiToCommon(SearchByPointItem item) {
		if (item == null) {
			return new PlaceSearchResult();
		}
		
		PlaceSearchResult common = new PlaceSearchResult();
		common.setPlaceName(item.getRoadAddress().getBuildingName());   // 명칭
		common.setAddress(item.getAddress().getAddressName());          // 주소(지번)
		common.setRoadAddress(item.getRoadAddress().getAddressName());  // 주소(도로명)
		
		return common;
	}
	
	/**
	 * 키워드로 조회한 위치 정보 결과를 공통 객체로 반환
	 * @param item
	 * @return
	 */
	private PlaceSearchResult convertResultKeywordApiToCommon(SearchByKeywordItem item) {
		if (item == null) {
			return new PlaceSearchResult();
		}
		
		PlaceSearchResult common = new PlaceSearchResult();
		common.setPlaceName(item.getPlaceName());           // 명칭
		common.setAddress(item.getAddressName());           // 주소(지번)
		common.setRoadAddress(item.getRoadAddressName());   // 주소(도로명)
		common.setCategory(item.getCategoryName());         // 카테고리
		common.setPlaceId(item.getId());                    // 장소 ID
		common.setPointByX(item.getX());                    // X좌표 - 경도
		common.setPointByY(item.getY());                    // Y좌표 - 위도
		
		return common;
	}
	
}
