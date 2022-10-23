package com.xct.bootdemo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileData {
	private String name;
	private Boolean isDir;
	private String length;
	
	public FileData(String name, Boolean isDir, long length) {
		this.name = name;
		this.isDir = isDir;
		setLength(length);
	}
	
	public void setLength(Long length) {
		if (isDir){
			this.length = "";
			return;
		}
		StringBuilder stringBuilder = new StringBuilder();
		if (length>1024) {
			stringBuilder.append(length / 1024);
			for (int i = stringBuilder.length() - 3; i > 0; i -= 3) {
				stringBuilder.insert(i, '_');
			}
			stringBuilder.append("KB");
		}else {
			stringBuilder.append(length).append('B');
		}
		this.length = stringBuilder.toString();
	}
}
