package com.xct.bootdemo.controller;

import com.xct.bootdemo.beans.Result;
import com.xct.bootdemo.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

@RestController
@RequestMapping("/files")
public class FileController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
	
	@GetMapping("{filePath}")
	public void getFile(@PathVariable String filePath, HttpServletRequest request, HttpServletResponse response){
		filePath = URLUtil.decode(filePath);
		
		LOG.info("filePath:"+filePath);
		
		try(FileInputStream inputStream = new FileInputStream("E:/"+filePath)) {
			response.reset();
			response.setContentType("application/octet-stream");
			String filename = filePath.substring(filePath.lastIndexOf('/')+1);
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
			ServletOutputStream outputStream = response.getOutputStream();
			byte[] b = new byte[4*1024];
			int len;
			//从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
			while ((len = inputStream.read(b)) > 0) {
				outputStream.write(b, 0, len);
			}
		} catch (FileNotFoundException e) {
			LOG.error("file path is fail:", e);
		} catch (IOException e) {
			LOG.error("io Exception:", e);
		}finally {
			LOG.info(response.getClass().getName());
		}
	}
}
