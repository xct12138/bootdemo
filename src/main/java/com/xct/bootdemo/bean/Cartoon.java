package com.xct.bootdemo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Cartoon")
public class Cartoon {
	@TableId(value = "cid", type = IdType.INPUT)
	private Integer cid;
	String ctname;
	private Integer firstchapter;
	private Integer chaptercount;
	private String introduction;
	
	public boolean isEmpty() {
		return (cid == 0) || ctname.equals("") || (firstchapter == 0) || (chaptercount == 0);
	}
	
	@Override
	public String toString() {
		return "Cartoon{" +
				"cid=" + cid +
				", ctname='" + ctname + '\'' +
				", firstchapter=" + firstchapter +
				", chaptercount=" + chaptercount +
				", introduction='" + introduction + '\'' +
				'}';
	}
}
