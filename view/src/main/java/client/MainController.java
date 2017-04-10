package client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller

@EnableWebMvc
public class MainController {


    @RequestMapping("/")
    public String home() {
        return "index";
    }
}

