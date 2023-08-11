package com.test.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {
	
	@Autowired
	private DataSource dataSource;
	
	
	@Test
	public void test() throws SQLException {
		Connection conn = dataSource.getConnection();
		System.out.println("conn : "+conn);
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(1,'불만있냐')");
		pstmt.executeUpdate();
	}
	@Test
	public void test2() throws SQLException {
		Connection conn = dataSource.getConnection();
		System.out.println("conn : "+conn);
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(2,'불만인냐')");
		pstmt.executeUpdate();
	}
	@Autowired
	private HikariDataSource datasource2;
	@Test
	public void test3() throws SQLException{
		Connection conn = datasource2.getConnection();
		System.out.println("conn : "+conn);
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(3,'불만인냐')");
		pstmt.executeUpdate();
	}
	

}