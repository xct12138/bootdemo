package com.xct.bootdemo.beans;

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
		StringBuilder stringBuilder = new StringBuilder(length.toString());
		if (length>1024) {
			for (int i = stringBuilder.length() - 4; i > 0; i -= 4) {
				stringBuilder.insert(i, '_');
			}
			stringBuilder.append("KB");
		}else {
			stringBuilder.append('B');
		}
		this.length = stringBuilder.toString();
	}
}
