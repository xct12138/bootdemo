package com.xct.bootdemo.filter;

import com.xct.bootdemo.controller.DownloadNovelController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class LoginFilter implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		request1.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		LOG.info(request1.getRequestURI());
		if (request1.getRequestURI().equals("")||request1.getRequestURI().equals("/")){
			LOG.info("main");
			request1.getRequestDispatcher("index.html").forward(request,response);
			return;
		}
		
		/*Cookie[] cookies = request1.getCookies();
		boolean logined = false;
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if ("uinfo".equals(cookie.getName()) && cookie.getValue().equals("123")) {
					
					chain.doFilter(request, response);
					
					logined = true;
					break;
				}
			}
		}*/
		chain.doFilter(request, response);
		//if (!logined) request.getRequestDispatcher("/login/document").forward(request,response);
	}
	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
