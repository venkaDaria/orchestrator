package com.globallogic.orchestrator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "forward:app.html";
    }

    @RequestMapping("/angular")
    public String angular() {
        return "forward:angular.html";
    }

    @RequestMapping("/template")
    public String template(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "template";
    }
}
