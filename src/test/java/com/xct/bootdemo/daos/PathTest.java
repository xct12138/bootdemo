package com.xct.bootdemo.daos;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;
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
