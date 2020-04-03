package com.demo.filterDemo;


import com.demo.hibernateDemo.TLUserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter  implements Filter {
    private String permitUrls[] = null;
    private String gotoUrl = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String permitUrls = filterConfig.getInitParameter("permitUrls");
        String gotoUrl = filterConfig.getInitParameter("gotoUrl");

        this.gotoUrl = gotoUrl;

        if (permitUrls != null && permitUrls.length() > 0) {
            this.permitUrls = permitUrls.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest res=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;

        if(!isPermitUrl(servletRequest)){
            if(filterCurrUrl(servletRequest)){
                System.out.println("--->请登录");
                resp.sendRedirect(res.getContextPath()+gotoUrl);
                return;
            }
        }
        System.out.println("--->允许访问");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        permitUrls = null;
        gotoUrl = null;
    }

    public boolean isPermitUrl(ServletRequest request) {
        boolean isPermit = false;
        String currentUrl = currentUrl(request);
        if (currentUrl.endsWith(".js")  || currentUrl.endsWith(".jpg")
                || currentUrl.endsWith(".png")  || currentUrl.endsWith(".gif")
                || currentUrl.endsWith(".css") || currentUrl.endsWith(".ico") ) {
            return true;
        }
        if (permitUrls != null && permitUrls.length > 0) {
            for (int i = 0; i < permitUrls.length; i++) {
                if (permitUrls[i].equals(currentUrl)) {
                    isPermit = true;
                    break;
                }
            }
        }
        return isPermit;
    }

    //请求地址
    public String currentUrl(ServletRequest request) {

        HttpServletRequest res = (HttpServletRequest) request;
        String path = res.getContextPath();
        String uri = res.getRequestURI();

        uri = uri.substring(path.length(), uri.length());

        System.out.println("当前请求地址:" + uri);
        return uri;
    }

    public boolean filterCurrUrl(ServletRequest request){

        boolean filter=false;
        HttpServletRequest res=(HttpServletRequest) request;
        TLUserInfo user =(TLUserInfo) res.getSession().getAttribute("user");
        if(null==user)
            filter=true;

        return filter;

    }
}
