package com.test.app;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.app.domain.mapper.MemoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisConfigTest {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void test() {
		System.out.println("sqlSessionFactory : "+ sqlSessionFactory);
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("SqlSession : "+ session);
		Connection conn = session.getConnection();
		System.out.println("Connection : " + conn);
	}
	
	@Autowired
	MemoMapper memoMapper;
	
	@Test
	public void test2() {
		System.out.println("memoMapper : " + memoMapper);
	}

}
