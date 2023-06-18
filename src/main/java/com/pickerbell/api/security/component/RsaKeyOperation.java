package com.pickerbell.api.security.component;

import com.pickerbell.api.security.domain.RsaKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 * 회원가입/로그인 시 구간 암호화를 위한 RSA 암호화 관련 코드
 */
@RequiredArgsConstructor
@Component
public class RsaKeyOperation {
	
	/**
	 * Public key를 이용하여 암호화
	 * @param target
	 * @param publicKey
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String encrypt(String target, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		byte[] encryptBytes = encryptCipher.doFinal(target.getBytes(StandardCharsets.UTF_8));
		String encrypted = Base64.getEncoder().encodeToString(encryptBytes);
		
		return encrypted;
	}
	
	/**
	 * Private key를 이용하여 복호화
	 * @param target
	 * @param privateKey
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decrypt(String target, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher decryptCipher = Cipher.getInstance("RSA");
		decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		
		byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(target));
		String decrypted = new String (decryptedBytes, StandardCharsets.UTF_8);
		
		return decrypted;
	}
	
	/**
	 * Public/Private 쌍으로 이루어진 RSA key를 생성.
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public RsaKey generateRsaKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		KeyPair pair = generator.generateKeyPair();
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
//		RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
		
		RsaKey generatedRsaKey = new RsaKey(
				null,
				publicKey,
				privateKey,
				publicKeySpec.getModulus().toString(16),
				publicKeySpec.getPublicExponent().toString(16),
				LocalDateTime.now().plusDays(1));   // 발급 시점으로부터 1일 후 만료. 무분별하게 발급받은 키가 쌓이는 것을 방지하기 위함
		
		return generatedRsaKey;
	}
	
}
