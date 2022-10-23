package com.xct.bootdemo.controller;

import com.xct.bootdemo.bean.Result;
import com.xct.bootdemo.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Reader;

@RestController
@RequestMapping("/novel")
public class DownloadNovelController {
	private static final Logger LOG = LoggerFactory.getLogger(DownloadNovelController.class);
	private DownloadService downloadService;
	@Autowired
	public void setDownloadService(DownloadService downloadService) {
		this.downloadService = downloadService;
	}
	
	@PostMapping("/download")
	public Result downloadNovel(HttpServletRequest request){
		Result result = new Result(3);
		try (Reader reader = request.getReader()){
			result = downloadService.downloadNovel(reader, result);
			
		} catch (IOException e) {
			result.setMsg("fail");
			LOG.error("request.getReader method exception", e);
		}
		return result;
	}
}
