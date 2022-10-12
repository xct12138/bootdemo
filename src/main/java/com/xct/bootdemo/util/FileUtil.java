package com.xct.bootdemo.util;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {
	public static List<String> readAllLines(String file) throws IOException {
		ArrayList<String> res = new ArrayList<>();
		InputStream in = new ClassPathResource(file).getInputStream();
		Scanner scanner = new Scanner(in);
		while (scanner.hasNextLine()){
			res.add(scanner.nextLine());
		}
		return res;
	}
	
	public static String readAllString(String file) throws IOException {
		StringBuilder res = new StringBuilder();
		try(InputStream inputStream = new ClassPathResource(file).getInputStream()){
			byte[] buffer = new byte[512];
			int rlen = 0;
			do{
				rlen = inputStream.read(buffer);
				res.append(new String(buffer,0,rlen));
			}while (rlen==512);
		}
		return res.toString();
	}
}
