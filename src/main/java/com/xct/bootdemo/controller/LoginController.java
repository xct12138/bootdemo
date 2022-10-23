package com.xct.bootdemo.controller;

import com.xct.bootdemo.bean.Result;
import com.xct.bootdemo.bean.User;
import com.xct.bootdemo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RestController()
//@RequestMapping("/login")
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	

	//private UserMapper userMapper;
	private static String indexHTML;
	private static void init(){
		try {
			indexHTML = FileUtil.readAllString("static\\login.html");
		} catch (IOException e) {
			LOG.error("login.html error:", e);
		}
	}
	//@GetMapping("/document")
	public String loginDocument(){
		if (indexHTML==null) init();
		return indexHTML;
	}
	//@PostMapping("/login")
	public Result login(
			@RequestParam("name")String name,
			@RequestParam("pwd") String pwd,
			HttpServletResponse response
	){
		User user = new User();//userMapper.searchUser(name,pwd);
		Result result = new Result(0,"success");
		if (user==null||user.getUid()==null){
//			userMapper.insert(new User(String.valueOf(name.hashCode()),name,pwd));
			result.setMsg("fail");
		}else response.addCookie(new Cookie("uid", user.getUid()));
		return result;
	}
}
