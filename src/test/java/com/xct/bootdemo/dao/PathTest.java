package com.xct.bootdemo.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class PathTest {
	private static final Logger Log = LoggerFactory.getLogger(PathTest.class);
	@Test
	public void test(){
		String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
		Log.info(path);
	}
}
