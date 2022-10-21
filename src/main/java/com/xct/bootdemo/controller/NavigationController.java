package com.xct.bootdemo.controller;

import com.xct.bootdemo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NavigationController {
	private static final Logger LOG = LoggerFactory.getLogger(NavigationController.class);
	
	@GetMapping("/")
	public String main(){
		return index();
	}
	@GetMapping("index.html")
	public String index(){
		return getHtml("index.html");
	}
	
	@GetMapping("visitDisk.html")
	public String visitDisk(){
		return getHtml("visitDisk.html");
	}
	
	@GetMapping("novelDownload.html")
	public String novelDownload(){
		return getHtml("novelDownload.html");
	}
	
	private String getHtml(String html){
		try {
			return FileUtil.readAllString("static\\"+html);
		} catch (IOException e) {
			LOG.error(html+" load fail", e);
		}
		return null;
	}
}
