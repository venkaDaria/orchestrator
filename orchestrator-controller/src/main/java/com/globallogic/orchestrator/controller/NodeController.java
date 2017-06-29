package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orchestrator/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @GetMapping()
    public Set<String> getAll() {
        return nodeService.load().stream().map(Node::asFormattedString).collect(Collectors.toSet());
    }

    @GetMapping("/{name}")
    public String getNode(@PathVariable final String name) {
        return nodeService.getByName(name).asFormattedString();
    }

    @PutMapping()
    public void add(@RequestParam final String name, @RequestParam final List<String> roles) {
        nodeService.add(name, roles);
    }

    @DeleteMapping("/{name}")
    public void remove(@PathVariable final String name) {
       nodeService.remove(name);
    }
}
