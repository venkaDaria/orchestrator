package com.globallogic.orchestrator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/", "/step1", "/step2", "/step3", "/step4", "/final"})
    public String index() {
        return "forward:index.html";
    }

    @RequestMapping({"/test"})
    public String test() {
        return "forward:karma-index.html";
    }
}
