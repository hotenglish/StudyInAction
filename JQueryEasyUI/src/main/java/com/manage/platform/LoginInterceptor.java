package com.manage.platform;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements Interceptor {

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        //获取ulr request
        ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        //获取session
        HttpSession session = request.getSession();
        //获取请求路径
        String path = request.getRequestURI();
        //判断是否登录
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/login.jsp");
        }
        return invocation.invoke();
    }

}
