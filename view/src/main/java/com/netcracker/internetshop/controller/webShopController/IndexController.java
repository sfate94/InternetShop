package com.netcracker.internetshop.controller.webShopController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }

}


