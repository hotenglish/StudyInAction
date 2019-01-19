package com.manage.platform;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        System.err.println(session.getAttribute("user"));
        System.out.println(((HttpServletRequest) servletRequest).getRequestURL());
        StringBuffer url = ((HttpServletRequest) servletRequest).getRequestURL();
        if (url.toString().contains(".action") && !url.toString().contains("USERLogin.action")) {
            if (session.getAttribute("user") == null) {
                servletRequest.getRequestDispatcher("/login.jsp");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
