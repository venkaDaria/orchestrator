package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/container")
public class ContainerController {

    @Autowired
    private Configuration config;

    @RequestMapping()
    public Set<String> getAll() {
        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));
        return containers.stream().map(Container::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{id}")
    public String getContainer(@PathVariable String id) {
        Container cont = null;
        for (Node node : config.getNodes()) {
            cont = node.getContainers().stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
            if (cont != null)
                break;
        }
        return cont.asFormattedString();
    }
}
