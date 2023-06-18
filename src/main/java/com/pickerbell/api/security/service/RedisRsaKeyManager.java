package com.pickerbell.api.security.service;

import com.pickerbell.api.security.component.RsaKeyOperation;
import com.pickerbell.api.security.domain.RsaKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * RSA key를 Redis 기반으로 관리하는 서비스
 */
@RequiredArgsConstructor
@Service
public class RedisRsaKeyManager implements RsaKeyManager {
	
	private static final String RSA_KEY_GROUP_NAME = "rsa_keys";
	
	private final RsaKeyOperation rsaKeyOperation;
	private final RedisTemplate<String, Object> redisTemplate;
	
	
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
		try {
			String key = "RSA_MEMORY_" + System.currentTimeMillis() + "_" + Math.abs(random.nextInt());
			RsaKey created = new RsaKey(key, rsaKey);
			
			HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
			operations.putIfAbsent(RSA_KEY_GROUP_NAME, created.getMemoryKey(), created);
			
			return created;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public RsaKey find(String key) {
		HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
		return (RsaKey) operations.get(RSA_KEY_GROUP_NAME, key);
	}
	
	@Override
	public boolean delete(RsaKey rsaKey) {
		try {
			HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
			operations.delete(RSA_KEY_GROUP_NAME, rsaKey.getMemoryKey());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
