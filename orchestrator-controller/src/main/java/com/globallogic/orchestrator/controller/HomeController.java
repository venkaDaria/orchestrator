package com.globallogic.orchestrator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String index() {
        LOG.debug("Redirect to Swagger UI");
        return "redirect:/swagger-ui.html";
    }
}
