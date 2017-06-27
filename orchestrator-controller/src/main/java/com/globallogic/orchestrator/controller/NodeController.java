package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.NodeService;
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
    private NodeService nodeService;

    @RequestMapping()
    public Set<String> getAll() {
        return nodeService.load().stream().map(Node::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{name}")
    public String getContainer(@PathVariable final String name) {
        return nodeService.getByName(name).asFormattedString();
    }
}
