package com.globallogic.orchestrator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping({"/", "/step1", "/step2", "/step3", "/step4", "/final"})
    public String index() {
        return "forward:index.html";
    }
}
