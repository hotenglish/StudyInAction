package com.manage.platform;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AnonymousFilter extends HttpServlet implements Filter {

    protected String encoding = null;
    private String[] trustPages;
    private List postfixs = new ArrayList();
    private String welcomePage;

    @Override
    public void destroy() {

    }

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		resp.setContentType("text/html; charset=UTF-8");
		if (trustPages != null) {
			String url = req.getRequestURL().toString();
			int index = url.lastIndexOf(".");
			int count = url.lastIndexOf("/");
			if (count < index) {
				String postfix = url.substring(index, url.length());
				if (!postfixs.contains(postfix)) {
					chain.doFilter(request, response);
					return;
				}
			}
			for (int i = 0; i < trustPages.length; i++) {
				if (url.endsWith(trustPages[i])) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		if (!checkLogined(session)) {
			resp.sendRedirect(req.getContextPath() + welcomePage);
			return;
		}
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		String strTrustPage = filterConfig.getInitParameter("trust-page");
		if (strTrustPage != null && !"".equals(strTrustPage)) {
			trustPages = strTrustPage.split(",");
		}
		if (trustPages != null) {
			for (int i = 0; i < trustPages.length; i++) {
				int index = trustPages[i].lastIndexOf(".");
				String postfix = trustPages[i].substring(index, trustPages[i].length());
				if (!postfixs.contains(postfix)) {
					postfixs.add(postfix);
				}
			}
		}
		welcomePage = filterConfig.getInitParameter("welcome-Page");
		if (!welcomePage.startsWith("/")) {
			welcomePage = "/" + welcomePage;
		}
	}

    private boolean checkLogined(HttpSession session) {
        Object obj = session.getAttribute("user");
        if (obj == null) {
            return false;
        }
        return true;
    }

}
