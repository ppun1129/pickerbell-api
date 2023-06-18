package com.pickerbell.api.security.domain;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;

/**
 * 발급된 RSA key 정보를 보관하는 클래스
 * 클라이언트에 JSON 형태로 값을 전달할 때에는 memoryKey, modules, publicExponent만 전달한다.
 */
@Getter
@AllArgsConstructor
@ToString
public class RsaKey implements Serializable {
	
	/**
	 * RsaKey 객체에 새로 발급한 memoryKey를 포함시켜 저장하기 위해 사용하는 생성자
	 * @param generatedMemoryKey 발급된 memoryKey
	 * @param origin (memoryKey가 없는 상태의)RsaKey 객체
	 */
	public RsaKey(String generatedMemoryKey, RsaKey origin) {
		this.memoryKey = generatedMemoryKey;
		this.publicKey = origin.publicKey;
		this.privateKey = origin.privateKey;
		this.modules = origin.modules;
		this.publicExponent = origin.publicExponent;
		this.expiresAt = origin.expiresAt;
	}
	
	@Expose
	private String memoryKey;
	
	private PublicKey publicKey;
	
	private PrivateKey privateKey;
	
	@Expose
	private String modules;
	
	@Expose
	private String publicExponent;
	
	private LocalDateTime expiresAt;
	
}
