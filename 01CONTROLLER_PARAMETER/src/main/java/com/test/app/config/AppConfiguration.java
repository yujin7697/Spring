package com.test.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.app.domain.dto.Person;

@Configuration
public class AppConfiguration {
	
	@Bean("myPerson")
	public Person PersonBean() {
		return new Person().builder()
				.name("유진")
				.age(12)
				.addr("대구")
				.build();
	}
}
