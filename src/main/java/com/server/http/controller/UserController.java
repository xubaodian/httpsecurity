package com.server.http.controller;

import com.server.http.entity.AjaxReult;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "123456");
        users.put("user1", "123456");
        users.put("user2", "123456");
    }
    //登录接口
    @PostMapping(value = "/login")
    public @ResponseBody AjaxReult login(HttpServletRequest req, HttpServletResponse res, @Param("username") String username, @Param("password") String password) {
        //此处密码没加密，实际应用中密码一定要加密处理
        AjaxReult ajaxReult = new AjaxReult("", 1000, null);
        if (null != username && null != password) {
            String getPassword = users.get(username);
            if (null != getPassword && getPassword.equals(password)) {
                //登录成功用户，发放token。这个值token可以采用常用方法生成，然后加密处理，这里就不展示了（例如jwt等等）
                req.getSession().setAttribute("token", "thisistest");
                req.getSession().setAttribute("name", username);
                //在session中添加权限字符串，判断该角色具有哪些曲线
                if ("admin".equals(username)) {
                    req.getSession().setAttribute("access", "01-02");
                } else {
                    req.getSession().setAttribute("access", "01-00");
                }
                ajaxReult.setObject("/index");
            } else {
                //登录失败，返回错误信息
                ajaxReult.setCode(1001);
                ajaxReult.setMessage("用户名或密码错误");
            }
        }
        return ajaxReult;
    }


    //退出接口
    @GetMapping(value = "logout")
    public @ResponseBody void logout(HttpSession httpSession, HttpServletResponse res) throws IOException {
        //使session失效
        httpSession.invalidate();
        res.setStatus(302);
        res.setHeader("location", "/logout");
        res.flushBuffer();
        return;
        //res.sendRedirect("/logout");
    }
}
