package com.test.app.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.app.domain.dto.MemoDto;
import com.test.app.domain.service.MemoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/memo")
public class RestController_02Memo {
	
	@Autowired
	private MemoService memoService;
	
	//메모확인전체
	@GetMapping(value="/getAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> getAll() {
		log.info("GET /memo/getAll");
		return memoService.getAllMemo();
	}
	//메모확인(단건)
	@GetMapping("/get/{id}")
	public void get(@PathVariable("id") int id) { //변수명이 같으면 ("id") 생략 가능
		log.info("GET /memo/get.. " + id);
	}
	//메모쓰기
	@PostMapping("/add")	//http://localhost:8080/app/memo/add
	public void add(@RequestBody MemoDto dto) { //post로 받을 때 requestbody를 작성해줘요
		log.info("POST /memo/add.. " + dto);
		memoService.addMemo(dto);
	}
	//메모수정
	@PutMapping("/put/{id}/{text}")
	public void put(MemoDto dto) {
		log.info("PUT /memo/put.." + dto);
	}
	@PutMapping("/put2")
	public void put2(@RequestBody MemoDto dto) {
		log.info("PUT /memo/put2.." + dto);
		memoService.modifyMemo(dto);
	}
//	patchMapping
	@PatchMapping("/patch/{id}/{text}")
	public void patch(MemoDto dto) {
		log.info("patch /memo/patch.." + dto);
	}
	//메모삭제
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable int id) {
		log.info("Delete /memo/remove.. " + id);
		memoService.removeMemo(id);
	}
}
