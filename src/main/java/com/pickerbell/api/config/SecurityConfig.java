package com.pickerbell.api.config;

import com.pickerbell.api.security.filter.JwtFilter;
import com.pickerbell.api.security.component.JwtOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtOperation jwtOperation;
	
	
	/**
	 * 인증 및 로그인/가입을 위한 URI 패턴을 무시(Spring Security 미적용)
	 * filter를 거치지 않도록 하기 위함
	 * @return WebSecurity
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("favicon.ico", "/user/**", "/auth/**");
	}
	
	/**
	 * 애플리케이션 전반 시큐리티 관련 설정
	 * JWT filter 추가, JWT를 통한 인증 후 처리를 위해 기본 HTTP 방식 미사용, 세션 미사용 처리
	 * @param http HttpSecurity
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf((csrf) -> csrf
						.disable())
				.httpBasic((basic) -> basic
						.disable())
				.sessionManagement((session) -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/auth/**", "/user/**").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(new JwtFilter(jwtOperation), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
