package com.server.http.filter;

import com.alibaba.fastjson.JSON;
import com.server.http.entity.AjaxReult;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//过滤器，拦截请求，
@WebFilter(filterName="CustomFilter",urlPatterns="/*")
public class LogonFilter implements Filter {

    //不拦截url列表
    private static final String [] ignoreUrls = {"/login", "/js/", "/logout"};

    //接口权限保存地址，可存储在数据库中，从数据库中读取
    private static final Map<String, String> interfaceAccess= new HashMap<>();

    /***
     * 访问/score/list接口，access权限字符串需包含01，
     * 访问/score/modify接口，access权限字符串需包含02
     */
    static {
        interfaceAccess.put("/score/list", "01");
        interfaceAccess.put("/score/modify", "02");
    }

    //过滤器初始化钩子函数
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("logon Filter init ==================================================");
    }

    //过滤请求
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)request;
        //不拦截请求直接通过
        String path = httpReq.getServletPath();
        for (String ignoreUrl : ignoreUrls) {
            Pattern pattern = Pattern.compile(ignoreUrl, Pattern.CASE_INSENSITIVE);
            if (pattern.matcher(path).find()) {
                chain.doFilter(request, response);
                return;
            }
        }
        //这一块是登录验证
        //拦截请求，未登录的直接返回需登录。
        HttpSession session = ((HttpServletRequest) request).getSession();
        //获取token
        Object token = session.getAttribute("token");
        //权限字符串
        Object access = session.getAttribute("access");
        //验证token，判断是否登录
        if (null != token && "thisistest".equals(token.toString())) {
            //这是权限验证，验证access，判断登录用户是否有访问该接口的权限，可以使用两个filter实例来分别操作，本例中放在一个里
            //访问接口所需的权限
            Object requireAuth = interfaceAccess.get(path);
            //requireAuth为null,代表不需权限验证，登录用户直接放行
            if (null != requireAuth) {
                //需要权限接口，且具有权限
                if (access != null && access.toString().indexOf(requireAuth.toString()) > -1) {
                    chain.doFilter(request, response);
                    return;
                } else {
                    //不具备权限
                    AjaxReult ajaxReult = new AjaxReult("系统无权限访问", 1005, null);
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(ajaxReult));
                    response.getWriter().close();
                    return;
                }
            } else {
                //无需权限接口
                chain.doFilter(request, response);
                return;
            }
        } else {
            ((HttpServletResponse)response).sendRedirect("/login");
        }
    }

    //过滤器注销钩子函数
    @Override
    public void destroy() {
        System.out.println("logon Filter destroy ==============================================");
    }
}
