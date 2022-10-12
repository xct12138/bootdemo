package com.xct.bootdemo.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PathFilter {
	private static Set<String> path;
	private static void init(){
		try {
			path = new HashSet<>();
			path.addAll(FileUtil.readAllLines(".pathignore"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean notFilter(File file){
		return notFilter(file.getAbsolutePath());
	}
	
	public static boolean notFilter(String absolutePath){
		if (path == null){
			init();
			if (path == null){
				return true;
			}
		}
		return !path.contains(absolutePath);
	}
}
