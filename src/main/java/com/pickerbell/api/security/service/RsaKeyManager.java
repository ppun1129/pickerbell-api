package com.pickerbell.api.security.service;

import com.pickerbell.api.security.domain.RsaKey;

import java.util.Random;

/**
 * 구간 암호화를 위한 RSA key를 저장/삭제 등 관리하는 서비스
 */
public interface RsaKeyManager {
	
	/**
	 * 랜덤 키 생성 시 사용하며, 어느 구현체라도 이 객체를 사용하도록 함
	 */
	Random random = new Random();
	
	/**
	 * 기본적인 형태의 RSA key memory key를 생성
	 * memory key : 생성된 RSA key를 별도의 저장소에 저장해 둘 때, 식별하기 위한 값
	 * @return
	 */
	default String generateMemoryKey() {
		return String.format("RSA_MEMORY_%d_%d", System.currentTimeMillis(), Math.abs(random.nextInt()));
	}
	
	/**
	 * 새로운 RSA key를 발급하고 저장
	 * @return 새로 발급된 RSA key
	 */
	RsaKey getGenerateAndSave();
	
	/**
	 * RSA key 정보를 저장하며 저장 시 해당 RSA key의 고유 키 값을 생성하여 반환
	 * @param rsaKey 저장할 RSA key 객체
	 * @return RsaKey - 발번된 고유 키 값이 포함된 RSA key 객체
	 */
	RsaKey save(RsaKey rsaKey);
	
	/**
	 * 고유 키 값으로 저장되어 있는 RSA key 조회
	 * @param key 저장된 RSA key의 키 값
	 * @return RsaKey - 저장되어 있는 RSA key 객체. 없을 경우 null 반환
	 */
	RsaKey find(String key);
	
	/**
	 * 해당하는 RSA key 객체를 제거
	 * @param rsaKey 제거 대상 RSA key 객체(RsaKey.memoryKey 값을 사용)
	 * @return 삭제 성공 시 true, 실패 시 false
	 */
	boolean delete(RsaKey rsaKey);
	
}
