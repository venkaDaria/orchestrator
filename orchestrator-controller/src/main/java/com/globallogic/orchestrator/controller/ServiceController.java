package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @RequestMapping()
    public Set<String> getAll() {
        return serviceService.load().stream().map(Service::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{name}")
    public String getContainer(@PathVariable String name) {
        return serviceService.getByName(name).asFormattedString();
    }
}
