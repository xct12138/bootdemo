package com.xct.bootdemo.util;

public class URLUtil {
	public static String decode(String url){
		return url.replaceAll("<", "[")
				.replaceAll("\\*", "]")
				.replaceAll(">","/");
	}
	
	public static String encode(String fileName){
		return fileName.replaceAll("\\[", "<").replaceAll("]", "*");
	}
}
