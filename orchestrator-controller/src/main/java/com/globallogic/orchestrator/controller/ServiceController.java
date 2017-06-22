package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
@Order(7)
public class ServiceController {

    @Autowired
    private Configuration config;

    @RequestMapping()
    public Set<String> getAll() {
        return config.getServices().stream().map(Service::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{name}")
    public String getContainer(@PathVariable String name) {
        for (Service service : config.getServices()) {
            if (service.getName().equals(name))
                return service.asFormattedString();
        }
        return null;
    }
}
