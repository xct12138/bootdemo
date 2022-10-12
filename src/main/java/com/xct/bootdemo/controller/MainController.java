package com.xct.bootdemo.controller;

import com.xct.bootdemo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/")
@ResponseBody
public class MainController {
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
	private static String indexHTML;
	private static void init(){
		try {
			indexHTML = FileUtil.readAllString("static\\index.html");
		} catch (IOException e) {
			LOG.error("main controller error:", e);
		}
	}
	
	@GetMapping
	public String indexHTML(){
		if (indexHTML==null) init();
		return indexHTML;
	}

}
