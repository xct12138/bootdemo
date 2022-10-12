package com.xct.bootdemo.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
	private Integer code;
	private Object data;
	private String msg;
	
	public Result(Integer code) {
		this.code = code;
	}
	
	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Result(Integer code, Object data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}
}
