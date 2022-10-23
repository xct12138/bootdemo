package com.xct.bootdemo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
@ToString
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * UID
	 */
	
	@TableId(type = IdType.INPUT)
	private String uid;
	
	/**
	 * 用户名
	 */
	private String uname;
	
	/**
	 * password
	 */
	private String pwd;
	
}