package com.server.http.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = "/*")
public class HttpFilter implements Filter {
    private static final String regEx = "/login|/js";
    private Pattern pattern = Pattern.compile(regEx);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init-----------------------------------------filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Cookie [] cookies = req.getCookies();
            if (null != cookies && cookies.length > 0) {
                for (Cookie item : cookies) {
                    if (item.getValue() == "32sfert4556") {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            }
        }
        res.setStatus(302);
        res.setHeader("Location", "/login");
    }

    @Override
    public void destroy() {
        System.out.println("init-----------filter");
    }
}
