package com.xct.bootdemo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoonNewChapter {
	@TableId(value = "pos", type = IdType.INPUT)
	private Integer pos;
	private Integer cid;
	
	public boolean isEmpty(){
		return pos==0||cid==0;
	}
	
	@Override
	public String toString() {
		return "CartoonNewChapter{" +
				"pos=" + pos +
				", cid=" + cid +
				'}';
	}
}
