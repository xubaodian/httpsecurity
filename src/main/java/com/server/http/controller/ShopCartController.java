package com.server.http.controller;

import com.server.http.entity.AjaxReult;
import com.server.http.entity.ShopInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shop")
public class ShopCartController {
    @GetMapping("/list")
    public @ResponseBody AjaxReult shopList() {
        AjaxReult ajaxReult = new AjaxReult("", 1000, null);
        ShopInfoVO shop = new ShopInfoVO("钢铁是怎样炼成的", 1);
        ShopInfoVO [] list = {shop};
        ajaxReult.setObject(list);
        return ajaxReult;
    }
}
