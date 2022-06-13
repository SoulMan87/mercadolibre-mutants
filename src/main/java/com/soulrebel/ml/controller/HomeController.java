package com.soulrebel.ml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {

    static final String REDIRECT_SWAGGER_UI_HTML = "redirect:swagger-ui.html";

    @GetMapping("/")
    public String index () {
        return REDIRECT_SWAGGER_UI_HTML;
    }
}
