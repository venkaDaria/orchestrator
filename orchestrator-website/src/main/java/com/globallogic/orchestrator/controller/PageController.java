package com.globallogic.orchestrator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping({"/", "/wizard/**"})
    public String index() {
        return "/index";
    }
}
