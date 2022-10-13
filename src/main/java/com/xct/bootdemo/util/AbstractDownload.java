package com.xct.bootdemo.util;


import com.xct.bootdemo.pojo.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractDownload {
	protected String aParentAttribute;
	protected String aParentAttributeValue;
	protected int aParentIndex;
	private static final Logger logger = LoggerFactory.getLogger(DownloadNovel.class);
	
	protected final int threadPoolSize = 8;
	protected URL url;
	protected String contentAttribute;
	protected String contentAttributeValue;
	protected int contentIndex;
	protected Decode decode;
	
	protected List<String> regex = new ArrayList<>();
	protected List<String> replacement = new ArrayList<>();
	
	public void download(String novelName, URL novelUrl, File parent,boolean order) {
		this.url = novelUrl;
		List<Chapter> chapters = getChapterList();
		int workLength = chapters.size()/threadPoolSize;
		String[] chaptersContent = new String[chapters.size()];
		CountDownLatch countDownLatch = new CountDownLatch(threadPoolSize);
		for (int i = 0; i < threadPoolSize; i++) {
			int finalI = i;
			new Thread(()->{
				for (int j = finalI*workLength; finalI < threadPoolSize-1?j < (finalI +1)*workLength : j < chapters.size(); j++) {
					chaptersContent[j] = getChapter(chapters.get(j),decode);
				}
				countDownLatch.countDown();
			}).start();
		}
		try {
			countDownLatch.await();
			writeTXT(novelName,chaptersContent,parent,order);
		} catch (InterruptedException e) {
			logger.error("countDown is interrupted", e);
		}
	}
	protected abstract List<Chapter> getChapterList();
	
	protected abstract String getChapter(Chapter chapter,Decode decode);
	public void chapterListParentPosition(String attribute,String attributeValue,int index) {
		this.aParentAttribute = attribute;
		this.aParentAttributeValue = attributeValue;
		this.aParentIndex = index;
	}
	
	public void chapterContentPosition(String attribute,String attributeValue,int index,Decode decode) {
		this.contentAttribute = attribute;
		this.contentAttributeValue = attributeValue;
		this.contentIndex = index;
		this.decode = decode;
	}
	
	public AbstractDownload contentReplaceAll(String regex,String replacement){
		this.regex.add(regex);
		this.replacement.add(replacement);
		return this;
	}
	public AbstractDownload contentReplaceAll(Map<String,String> regexMapReplacement){
		regexMapReplacement.forEach((regex,replacement)->{
			this.regex.add(regex);
			this.replacement.add(replacement);
		});
		return this;
	}
	protected void writeTXT(String name, String[] chaptersContent, File parent,boolean order){
		File novelFile = new File(parent, name+".txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(novelFile);
			if (!novelFile.exists()&&!novelFile.createNewFile()) {
				logger.error("txt creat failed");
				return;
			}
			if (order) for (String s : chaptersContent) {
					fileWriter.write(s);
					fileWriter.flush();
				}
			else for (int i = chaptersContent.length - 1; i >= 0; i--) {
				fileWriter.write(chaptersContent[i]);
				fileWriter.flush();
			}
			
			logger.info("{}----flush",novelFile.getAbsolutePath());
			fileWriter.close();
		}catch (IOException e) {
			logger.error("file write failed",e);
		}finally {
			try {
				assert fileWriter != null;
				fileWriter.close();
			} catch (IOException e) {
				logger.error("file closed failed",e);
			}
		}
	}
	
	protected Document getDocument(String url) throws MalformedURLException {
		return getDocument(new URL(url));
	}
	protected Document getDocument(URL url) {
		Document document = null;
		while (document == null){
			try {
				document = Jsoup.parse(url,3000);
			} catch (IOException e) {
				logger.error("Document acquisition failed",e.getCause());
			}
		}
		return document;
	}
}
