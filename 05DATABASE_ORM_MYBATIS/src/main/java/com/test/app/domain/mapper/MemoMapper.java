package com.test.app.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.test.app.domain.dto.MemoDto;

@Mapper
public interface MemoMapper {
	
	@Insert("insert into tbl_memo values(#{id},#{text})")
	public int insert(MemoDto dto);
	
}
