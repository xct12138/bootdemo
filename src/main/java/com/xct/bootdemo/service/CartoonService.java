package com.xct.bootdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.javafx.logging.PulseLogger;
import com.xct.bootdemo.bean.Cartoon;
import com.xct.bootdemo.bean.CartoonNewChapter;
import com.xct.bootdemo.dao.CartoonMapper;
import com.xct.bootdemo.dao.NewChapterMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartoonService {
	private CartoonMapper ctMapper;
	private NewChapterMapper ncMapper;
	@Autowired
	private void init(CartoonMapper ctMapper ,NewChapterMapper ncMapper){
		this.ctMapper = ctMapper;
		this.ncMapper = ncMapper;
	}
	
	public void add(Cartoon cartoon,List<CartoonNewChapter> newChapters){
		ctMapper.insert(cartoon);
		for (CartoonNewChapter newChapter : newChapters) ncMapper.insert(newChapter);
	}
	
	public List<Cartoon> search(String cartoon_name){
		val wrapper = new LambdaQueryWrapper<Cartoon>();
		wrapper.like(Cartoon::getCtname,cartoon_name);
		return ctMapper.selectList(wrapper);
	}
	public List<Cartoon> searchAll(long pageStart,long size){
		IPage<Cartoon> page = new Page<>(pageStart,size);
		val selectPage = ctMapper.selectPage(page, null);
		return selectPage.getRecords();
	}
	public List<CartoonNewChapter> searchNewChapter(int cid){
		return ncMapper.selectByCid(cid);
	}
}
