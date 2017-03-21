package com.sinosoft.shiro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by oracle on 2017-03-19.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost() {
        System.out.println("+++++++++++++++++++++++++");
        return null;
    }

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/unauthorize")
    public String unauthorize() {
        return "unauthorizel";
    }

    @RequestMapping("/toLogout")
    public String toLogout() {
        return "toLogout";
    }

    @RequestMapping("/edict")
    public String edict() {
        return "edict";
    }

    @RequestMapping("/read")
    public String read() {
        return "read";
    }
}
