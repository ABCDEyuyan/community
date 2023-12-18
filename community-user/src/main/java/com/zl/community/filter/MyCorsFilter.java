package com.zl.community.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zl.community.constant.Constants.Other.OPTIONS;

/**
 * @Author : ZL
 */
public class MyCorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp= (HttpServletResponse)response;
        HttpServletRequest req= (HttpServletRequest)request;
        resp.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:5500");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req,resp);
        System.out.println("myCoresFilter");
    }
}
