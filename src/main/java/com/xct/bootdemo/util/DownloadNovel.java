package com.xct.bootdemo.util;


import com.xct.bootdemo.pojo.Chapter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class DownloadNovel extends AbstractDownload {
	private static final Logger logger = LoggerFactory.getLogger(DownloadNovel.class);
	
	@Override
	protected List<Chapter> getChapterList(){
		List<Chapter> chapters1 = new ArrayList<>();
		Document catalogue = getDocument(url);
		String protocolHeader = url.getProtocol();
		logger.info("Catalogue downloaded finished");
		Elements parents = catalogue.getElementsByAttributeValue(aParentAttribute, aParentAttributeValue);
		Elements catalogueURLList = parents.get(aParentIndex).getElementsByTag("a");
		for (int i = 6; i < catalogueURLList.size(); i++) {
			String name = catalogueURLList.get(i).text();
			String href = catalogueURLList.get(i).attr("href");
			if (!href.startsWith(protocolHeader)){
				href = protocolHeader+"://" +url.getAuthority()+href;
			}
			chapters1.add(new Chapter(name,href));
		}
		
		logger.info("Catalogue resolution completed");
		return chapters1;
	}
	
	@Override
	protected String getChapter(Chapter chapter,Decode decode){
		Document document = null;
		try {
			document = getDocument(chapter.getUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assert document != null;
		Element content1 = document.getElementsByAttributeValue(contentAttribute, contentAttributeValue).get(contentIndex);
		String content = decode!=null?decode.decode(content1.html()):content1.html();
		
		content = chapter.getName() + content.substring(2)
				.replaceAll("&nbsp;|&nbs;|&amp;nbsp;|&amp;nbs;|<p.*</p>|<div.*</div>|\"", "")
				.replaceAll("<br>+|\n+", "\n");
		for (int i = 0; i < regex.size(); i++) {
			content = content.replaceAll(regex.get(i), replacement.get(i));
		}
		if (content.indexOf("<script>") > 0)
			content = content.substring(0, content.indexOf("<script>"));
		logger.info("解析完毕:{}",chapter.getName());
		return content;
	}
}