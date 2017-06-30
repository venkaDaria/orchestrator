package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/node", produces = MediaType.APPLICATION_JSON_VALUE)
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @GetMapping()
    public Set<Node> getAll() {
        return nodeService.load();
    }

    @GetMapping("/{name}")
    public Node getNode(@PathVariable final String name) {
        return nodeService.getByName(name);
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
