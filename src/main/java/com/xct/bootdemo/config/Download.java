package com.xct.bootdemo.config;

import com.xct.bootdemo.util.DownloadNovel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Download {
	
	@Bean
	public DownloadNovel createDownloadNovel(){
		return new DownloadNovel();
	}
}
