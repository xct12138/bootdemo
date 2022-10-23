package com.xct.bootdemo.controller;

import com.xct.bootdemo.bean.FileData;
import com.xct.bootdemo.bean.Result;
import com.xct.bootdemo.util.PathFilter;
import com.xct.bootdemo.util.URLUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paths")
public class PathController {
	private static final File E=new File("E:\\");
	private static final Logger LOG = LoggerFactory.getLogger(PathController.class);
	@GetMapping
	public Result getChildren(){
		return getChildren("");
	}
	@SneakyThrows
	@GetMapping("/{parent}")
	public Result getChildren(@PathVariable String parent){
		parent = URLUtil.decode(parent);
		
		LOG.info("path:"+parent);
		
		File p = new File(E, parent);
		File[] list = p.listFiles();
		Result result = new Result(0);
		result.setMsg("success");
		List<FileData> fileData = new ArrayList<>();
		if (list!=null) {
			BasicFileAttributes basicFileAttributes;
			for (File file : list) {
				if (PathFilter.notFilter(file)) {
					basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
					boolean isDir = basicFileAttributes.isDirectory();
					fileData.add(new FileData(URLUtil.encode(file.getName()), isDir, isDir ? 0 : basicFileAttributes.size()));
				}
			}
			result.setData(fileData);
		}else if (!p.exists())result.setMsg("fail");
		 else {
			 fileData.add(new FileData(URLUtil.encode(p.getName()),false,p.length()));
			 result.setData(fileData);
		}
		return result;
	}
}
