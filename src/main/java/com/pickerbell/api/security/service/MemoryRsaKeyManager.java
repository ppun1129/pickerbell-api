package com.pickerbell.api.security.service;

import com.pickerbell.api.security.component.RsaKeyOperation;
import com.pickerbell.api.security.domain.RsaKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * RSA key를 애플리케이션 메모리 기반으로 관리하는 서비스
 */
@RequiredArgsConstructor
@Service
public class MemoryRsaKeyManager implements RsaKeyManager {
	
	Map<String, RsaKey> keys = new HashMap<>();
	
	private final RsaKeyOperation rsaKeyOperation;
	
	
	@Override
	public RsaKey getGenerateAndSave() {
		try {
			RsaKey generatedRsaKey = rsaKeyOperation.generateRsaKey();
			return save(generatedRsaKey);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public RsaKey save(RsaKey rsaKey) {
		RsaKey created = new RsaKey(generateMemoryKey(), rsaKey);
		keys.put(created.getMemoryKey(), created);
		return created;
	}
	
	@Override
	public RsaKey find(String key) {
		return keys.get(key);
	}
	
	@Override
	public boolean delete(RsaKey rsaKey) {
		return keys.remove(rsaKey.getMemoryKey()) != null;
	}
	
}
