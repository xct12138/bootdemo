package com.xct.bootdemo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Result)) return false;
		
		Result result = (Result) o;
		
		if (!Objects.equals(code, result.code)) return false;
		if (!Objects.equals(data, result.data)) return false;
		return Objects.equals(msg, result.msg);
	}
	
	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + (msg != null ? msg.hashCode() : 0);
		return result;
	}
}
