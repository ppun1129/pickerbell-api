package com.pickerbell.api.security.filter;

import com.pickerbell.api.security.component.JwtOperation;
import com.pickerbell.api.user.domain.UserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 인증 처리를 위한 필터. 하나의 요청에 한 번만 체크하기 위해 추상클래스 OncePerRequestFilter를 구현
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtOperation jwtOperation;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String jwt = request.getHeader("jwt");
		
		try {
			UserInfo user = jwtOperation.parseTokenToUserInfo(jwt);
		} catch (RuntimeException e) {
			throw e;
		}
		
		filterChain.doFilter(request, response);
	}
}
