package com.xct.bootdemo.dao;

import com.xct.bootdemo.beans.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PathTest {
	private static final Logger Log = LoggerFactory.getLogger(PathTest.class);
	
	@Autowired
	private UserMapper userMapper;
	@Test
	public void test(){
		User user = userMapper.searchUser("123", "123");
		Log.info(user.toString());
	}
}
