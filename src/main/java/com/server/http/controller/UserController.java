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

@Controller
@RequestMapping(value = "/user")
public class UserController {

    //登录接口
    @PostMapping(value = "/login")
    public @ResponseBody AjaxReult login(HttpServletRequest req, @Param("username") String username, @Param("password") String password) {
        //此处密码没加密，实际应用中密码一定要加密处理
        AjaxReult ajaxReult = new AjaxReult("", 1000, null);
        if ("admin".equals(username) && "123456".equals(password)) {
            req.getSession().setAttribute("token", "thisistest");
            ajaxReult.setObject("/index");
        } else {
            ajaxReult.setCode(1001);
            ajaxReult.setMessage("用户名或密码错误");
        }
        return ajaxReult;
    }

    //退出接口
    @GetMapping(value = "logout")
    public @ResponseBody AjaxReult logout(HttpSession httpSession, HttpServletResponse res) throws IOException {
        httpSession.invalidate();
        AjaxReult ajaxReult = new AjaxReult("", 1000, null);
        res.sendRedirect("/logout");
        return ajaxReult;
    }
}
