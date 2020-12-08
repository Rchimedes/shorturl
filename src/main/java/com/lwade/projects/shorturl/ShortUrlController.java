package com.lwade.projects.shorturl;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShortUrlController {

    @GetMapping("/")
    public String index()  {
        return "index";
    }
}
