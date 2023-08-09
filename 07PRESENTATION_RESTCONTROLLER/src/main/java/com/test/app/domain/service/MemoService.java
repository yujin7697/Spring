package com.test.app.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.app.domain.dto.MemoDto;
import com.test.app.domain.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemoService {
	
	@Autowired
	private MemoMapper mapper;
	
//	전체메모 가져오기
	public List<MemoDto> getAllMemo(){
		log.info("MemoService's getAllMemo call!");
		
		return mapper.selectAll();
	}
//	메모작성
	public void addMemo(MemoDto dto) {
		log.info("MemoService's addMemo call!");
		mapper.insert(dto);
	}
//	메모작성
	@Transactional(rollbackFor = Exception.class) //모든 예외에 대해서 롤백하겠다.
	public void addMemoTx(MemoDto dto) {
		log.info("MemoService's addMemoTx call!");
		int id = dto.getId();
		mapper.insert(dto);//01 정상 insert
		dto.setId(id); //pk오류 발생예정인 dto
		mapper.insert(dto);//02 pk 오류 발생
	}
//	메모수정
	@Transactional(rollbackFor = Exception.class)
	public void modifyMemo(MemoDto dto) {
		log.info("MemoService's modifyMemo call");
		mapper.update(dto);
	}
//	메모삭제
	@Transactional(rollbackFor = Exception.class)
	public void removeMemo(@PathVariable int id) {
		log.info("MemoService's delete call");
		mapper.delete(id);
	}

	
}
