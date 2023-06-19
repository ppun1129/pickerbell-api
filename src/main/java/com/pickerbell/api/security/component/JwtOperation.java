package com.pickerbell.api.security.component;

import com.pickerbell.api.user.domain.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtOperation {
	
	private static SecretKey secretKey;
	
	@Value("${jwt.key}")
	private String KEY;
	
	@PostConstruct
	private void init() {
		secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
	}
	
	/**
	 * 지정된 정보를 담아 JWT 생성
	 * @param user
	 * @return
	 */
	public String createUserInfoToken(UserInfo user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("userId", user.getUserId());
		claims.put("userNickname", user.getUserNickname());
		
		Date expireAt = new Date();
		expireAt.setTime(expireAt.getTime() + (1000 * 60 * 60));    // 1시간동안 유효
		
		String jws = Jwts.builder()
				.setClaims(claims)
				.setExpiration(expireAt)
				.signWith(secretKey)
				.compact();
		
		return jws;
	}
	
	/**
	 * 받은 토큰을 파싱하여 사용자 정보 반환
	 * @param jws
	 * @return
	 * @throws RuntimeException 토큰이 유효하지 않을 경우 예외 발생
	 */
	public UserInfo parseTokenToUserInfo(String jws) throws RuntimeException {
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jws);
		Claims body = claimsJws.getBody();
		
		UserInfo user = new UserInfo();
		user.setId(Double.valueOf(body.get("id", Double.class)).longValue());
		user.setUserId(body.get("userId", String.class));
		user.setUserNickname(body.get("userNickname", String.class));
		
		return user;
	}
	
}
