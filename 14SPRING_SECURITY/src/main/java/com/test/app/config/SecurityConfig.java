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
				.antMatchers("/","/public").permitAll()
				.antMatchers("/user").hasRole("USER")		//ROLE_USER
				.antMatchers("/member").hasRole("MEMBER")	//ROLE_MEMBER
				.antMatchers("/admin").hasRole("ADMIN")		//ROLE_ADMIN
				.anyRequest().authenticated()				//나머지 URL은 모두 인증작업이 완료된 이후 접근가능
			.and()
			.formLogin()
			
			
			.and()
			.logout();
	}
	
	
	// 인증처리 함수
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user")
				.password(passwordEncoder.encode("1234"))
				.roles("USER");
		auth
			.inMemoryAuthentication()
				.withUser("member")
				.password(passwordEncoder.encode("1234"))
				.roles("MEMBER");
		auth
			.inMemoryAuthentication()
				.withUser("admin")
				.password(passwordEncoder.encode("1234"))
				.roles("ADMIN");
	}
	
	

	
	// BCryptPasswordEncoder Bean 등록
	// 패스워드 검증에 사용
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	

	
}