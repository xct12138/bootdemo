package com.xct.bootdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xct.bootdemo.bean.Result;
import com.xct.bootdemo.util.DownloadNovel;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DownloadService {
	private static final Logger LOG = LoggerFactory.getLogger(DownloadService.class);
	
	private static final List<RequestContent> requestContents = new ArrayList<>();
	private DownloadNovel downloadNovel;
	@Autowired
	public void setDownloadNovel(DownloadNovel downloadNovel){
		this.downloadNovel = downloadNovel;
	}
	
	public Result downloadNovel(Reader reader,Result result){
		try{
			ObjectMapper mapper = new ObjectMapper();
			RequestContent rc = mapper.readValue(reader, RequestContent.class);
			
			LOG.info("download:\nname:{}\turl:{}\t",rc.novelName,rc.novelUrl);
			
			if (requestContents.contains(rc)) {
				result.setMsg("fail");
				result.setData("重复请求");
				return result;
			}
			requestContents.add(rc);
			new Thread(()->{
				try {
					downloadNovel.chapterListParentPosition(rc.chapterListPosition.attribute, rc.chapterListPosition.value, rc.chapterListPosition.index);
					downloadNovel.chapterContentPosition(rc.chapterContentPosition.attribute, rc.chapterContentPosition.value, rc.chapterContentPosition.index,null);
					for (RegexReplacement replacement : rc.replacements) {
						downloadNovel.contentReplaceAll(replacement.regex,replacement.replacement);
					}
					downloadNovel.download(rc.novelName, new URL(rc.novelUrl),new File("E:\\recreation\\download\\novel"),rc.order);
				} catch (MalformedURLException e) {
					LOG.error("novel url fail", e);
				}
			}).start();
			result.setMsg("success");
		} catch (IOException e) {
			LOG.error("请求内容引起的IO异常", e);
		}
		
		return result;
	}
	
	@Data
	@ToString
	static class RequestContent{
		private String novelName;
		private String novelUrl;
		private Boolean order;
		private Attr chapterListPosition;
		private Attr chapterContentPosition;
		private RegexReplacement[] replacements;
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			RequestContent that = (RequestContent) o;
			return Objects.equals(novelName, that.novelName) && Objects.equals(novelUrl, that.novelUrl);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(novelName, novelUrl);
		}
	}
	
	@Data
	@ToString
	static class Attr{
		private String attribute;
		private String value;
		private Integer index;
	}
	
	@Data
	@ToString
	static class RegexReplacement{
		private String regex;
		private String replacement;
	}
}
