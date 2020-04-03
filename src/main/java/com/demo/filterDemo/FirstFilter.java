package com.demo.filterDemo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(filterName = "firstFilter",value = {"/*"})
public class FirstFilter implements Filter {

    private FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //下面代码用户用户请求的预处理
        //获取ServletContext对象，用户记录日志
        ServletContext servletContext=config.getServletContext();
        long before=System.currentTimeMillis();
        System.out.println("开始过滤。。。。");
        //将请求转化成HttpServletRequest请求
        HttpServletRequest hrequest=(HttpServletRequest) servletRequest;
        //输出提示信息
        System.out.println("已经截获到用户的请求地址："+hrequest.getServletPath());

        //filter 只是链式处理，请求依然放行到目的地址
        filterChain.doFilter(servletRequest,servletResponse);

        //响应后处理
        long after=System.currentTimeMillis();
        System.out.println("过滤结束。。。");

        System.out.println("请求被定位到"+hrequest.getRequestURI()+"所花时间为："+(after-before));


    }

    @Override
    public void destroy() {
        config=null;
    }
}
