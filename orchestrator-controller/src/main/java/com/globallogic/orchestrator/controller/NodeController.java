package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private Configuration config;

    @RequestMapping()
    public Set<String> getAll() {
        return config.getNodes().stream().map(Node::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{name}")
    public String getContainer(@PathVariable String name) {
        for (Node node : config.getNodes()) {
            if (node.getName().equals(name))
                return node.asFormattedString();
        }
        return null;
    }
}
