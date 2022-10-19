package com.xct.bootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xct.bootdemo.beans.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
	
	@Select("select * from test.user where uname = #{uname} and pwd = #{pwd}")
	User searchUser(@Param("uname") String uname,@Param("pwd") String pwd);
}
