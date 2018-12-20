package com.server.http.controller;

import com.server.http.entity.AjaxReult;
import com.server.http.entity.ScoreInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/score")
public class ScoreController {

    private static Map<String, ScoreInfoVO> scoreMap = new HashMap<>();
    static {
        scoreMap.put("user1", new ScoreInfoVO("user1","数学", 90));
        scoreMap.put("user2", new ScoreInfoVO("user2","数学", 95));
    }

    @GetMapping("/list")
    public @ResponseBody AjaxReult getScore(HttpServletRequest req) {
        Object name = req.getSession().getAttribute("name");
        AjaxReult ajaxReult = new AjaxReult("", 1000, null);
        if (null != name) {
            ScoreInfoVO score = scoreMap.get(name);
            if (null != score) {
                ScoreInfoVO[] list = {score};
                ajaxReult.setObject(list);
            } else {
                Collection<ScoreInfoVO> scoreInfoVOS = scoreMap.values();
                ajaxReult.setObject(scoreInfoVOS.toArray());
            }
        }
        return ajaxReult;
    }

    @PutMapping("/modify")
    public @ResponseBody AjaxReult setScore(String name, Integer score) {
        AjaxReult ajaxReult = new AjaxReult("参数错误", 1003, null);
        if (null != name && null != score) {
            Object result = scoreMap.get(name);
            if (null != result) {
                scoreMap.put(name, new ScoreInfoVO(name,"数学", score));
                ajaxReult.setCode(1000);
                ajaxReult.setMessage("Ok");
            }
        }
        return ajaxReult;
    }
}
