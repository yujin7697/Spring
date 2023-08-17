package com.test.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.app.config.auth.CustomAccessDeniedHandler;
import com.test.app.config.auth.CustomAuthenticationEntryPoint;
import com.test.app.config.auth.CustomAuthenticationFailureHandler;
import com.test.app.config.auth.CustomLoginSuccessHandler;
import com.test.app.config.auth.CustomLogoutHandler;
import com.test.app.config.auth.CustomLogoutSuccessHandler;
import com.test.app.config.auth.PrincipalDetailsService;

//security-context.xml 설정 내용

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 웹 요청 처리 	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable();
		
		http
			.authorizeRequests()
				.antMatchers("/","/public","/myLogin","/join").permitAll()
				.antMatchers("/user").hasRole("USER")		//ROLE_USER
				.antMatchers("/member").hasRole("MEMBER")	//ROLE_MEMBER
				.antMatchers("/admin").hasRole("ADMIN")		//ROLE_ADMIN
				.anyRequest().authenticated()				//나머지 URL은 모두 인증작업이 완료된 이후 접근가능
			.and()
			
			//로그인
			.formLogin()
			.loginPage("/myLogin")
			.successHandler(new CustomLoginSuccessHandler())//ROLE_USER -> user페이지 / ROLE_MEMBER -> member페이지
			.failureHandler(new CustomAuthenticationFailureHandler())
			
			.and()
			//로그아웃
			.logout()
			.logoutUrl("/logout")
			.permitAll()
			.addLogoutHandler(new CustomLogoutHandler())							//세션초기화
			.logoutSuccessHandler(new CustomLogoutSuccessHandler())				//기본위치로 페이지이동
			
			.and()
			//예외처리
			.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint())		//인증이 필요한 자원에 접근 예외처리
			.accessDeniedHandler(new CustomAccessDeniedHandler());											//권한 실패 예외처리
		
			
	}
	
	@Autowired
	private PrincipalDetailsService principalDetailsService;
	
	// 인증처리 함수
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("user")
//				.password(passwordEncoder.encode("1234"))
//				.roles("USER");
//		auth
//			.inMemoryAuthentication()
//				.withUser("member")
//				.password(passwordEncoder.encode("1234"))
//				.roles("MEMBER");
//		auth
//			.inMemoryAuthentication()
//				.withUser("admin")
//				.password(passwordEncoder.encode("1234"))
//				.roles("ADMIN");
		
		auth.userDetailsService(principalDetailsService)
			.passwordEncoder(passwordEncoder);
		
	}
	
	

	
	// BCryptPasswordEncoder Bean 등록
	// 패스워드 검증에 사용
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	

	
}