package com.xct.bootdemo.controller;

import com.xct.bootdemo.beans.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class UploadController {
	private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
	
	@PostMapping("/upload")
	public Result upload(@RequestParam("file")MultipartFile file){
		return saveFile(file);
	}
	@PostMapping("/multiUpload")
	public Object multiUpload(@RequestParam("file")MultipartFile[] files){
		for (MultipartFile f : files){
			saveFile(f);
		}
		return "ok";
	}
	private Result saveFile(MultipartFile file){
		Result result = new Result();
		result.setCode(2);
		if (file.isEmpty()){
			result.setMsg("upload fail");
			return result;
		}
		String filename = file.getOriginalFilename(); //获取上传文件原来的名称
		String filePath = "E:\\recreation\\download\\新建文件夹 (2)\\";
		File temp = new File(filePath);
		if (!temp.exists()) {
			temp.mkdirs();
		}
		
		File localFile = new File(filePath+filename);
		try {
			file.transferTo(localFile); //把上传的文件保存至本地
			LOG.info(file.getOriginalFilename()+" 上传成功");
		}catch (IOException e){
			LOG.error("upload fail", e);
			result.setMsg("upload fail");
			return result;
		}
		result.setMsg("upload success");
		return result;
	}
}
