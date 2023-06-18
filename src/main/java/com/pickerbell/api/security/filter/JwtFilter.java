package com.pickerbell.api.security.filter;

import com.pickerbell.api.user.domain.UserInfo;
import com.pickerbell.api.security.component.JwtOperation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * (미구현)JWT 인증 처리를 위한 필터. 하나의 요청에 한 번만 체크하기 위해 추상클래스 OncePerRequestFilter를 구현
 */
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtOperation jwtOperation;
	
	public JwtFilter(JwtOperation jwtOperation) {
		this.jwtOperation = jwtOperation;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String jwt = request.getHeader("jwt");
		
		try {
			// TODO : 토큰을 파싱하여 유효한 정보 여부를 확인 후 처리
			UserInfo user = jwtOperation.parseToken(jwt);
		} catch (RuntimeException e) {
			throw e;
		}
		
		filterChain.doFilter(request, response);
	}
}
