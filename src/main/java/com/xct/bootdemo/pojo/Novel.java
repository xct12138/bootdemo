package com.xct.bootdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Novel {
	private String novelName;
	private String url;
	private String introduction;

}
