package com.test.app;

import org.junit.Test;

import com.test.app.domain.dto.MemoDto;
import com.test.app.domain.dto.Person;

public class lombokTests {

	@Test
	public void test1() {
		Person obj = new Person();
		obj.setName("먀먀");
		obj.setAddr("먀먀먀");
		obj.setAge(1);
		System.out.println(obj);
		
	}
	@Test
	public void test2() {
		MemoDto dto = MemoDto.builder()
						.writer("똥")
						.id(2)
						.text("내용")
						.build();
		System.out.println("DTO : "+dto);
	}

}
