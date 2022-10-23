package com.xct.bootdemo;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ALLTest {
	@Test
	public void test() throws MalformedURLException {
		URL url = new URL("https://baidu.com");
		System.out.println(url.getProtocol());
	}
	
}
