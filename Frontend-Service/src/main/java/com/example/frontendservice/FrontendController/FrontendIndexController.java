package com.example.frontendservice.FrontendController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendIndexController {

    @RequestMapping("/index")
    public String goToIndex(){
        return "redirect:http://localhost:9090";
    }

}