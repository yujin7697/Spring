package com.test.app.controller;

import java.io.FileNotFoundException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	
	@GetMapping("/form")
	public void get() {
		log.info("GET /memo/form...");
	}
	@PostMapping("/post")
	public String post(@Valid MemoDto dto,BindingResult bindingResult,Model model) {
		log.info("POST /memo/post... "+dto);
		log.info("POST /memo/post... isError "+bindingResult.getFieldError("id"));
		if(bindingResult.hasFieldErrors()) {
//			log.info("POST /memo/post... isError "+bindingResult.getFieldError("id").getDefaultMessage());
			for( FieldError error  : bindingResult.getFieldErrors()) {
				log.info(error.getField()+ " : " + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());		
			}
			return "memo/form";
		}
			
		return "";
	}
	
	@GetMapping("/list1")
	public void list1() throws Exception{	
		System.out.println("GET /memo/list1");
		throw new FileNotFoundException("파일이 없다..");
	}
	
	@GetMapping("/list2")
	public void list2() throws Exception{
		System.out.println("GET /memo/list2");
		throw new ArithmeticException("계산똑바로해..");

	}
	
	@GetMapping("/list3/{n1}/{n2}")
	public void list3(@PathVariable int n1, @PathVariable int n2) {
		System.out.println("GET /memo/list3");
		System.out.println("계산결과 : " + (n1/n2));

	}	
	@GetMapping("/list4")
	public void list4() {
		System.out.println("GET /memo/list4");
		throw new NullPointerException();
	}
}