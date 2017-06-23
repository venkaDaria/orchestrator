package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/container")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @RequestMapping()
    public Set<String> getAll() {
        return containerService.load().stream().map(Container::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{id}")
    public String getContainer(@PathVariable String id) {
        return containerService.getById(id).asFormattedString();
    }
}
