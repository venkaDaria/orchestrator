package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/node", produces = MediaType.APPLICATION_JSON_VALUE)
public class NodeController {

    private static final Logger LOG = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private NodeService nodeService;

    @GetMapping()
    public Set<Node> getAll() {
        LOG.debug("Get all nodes request");
        return nodeService.load();
    }

    @GetMapping("/{name}")
    public Node getNode(@PathVariable final String name) {
        LOG.debug("Get a node by id request");
        return nodeService.getByName(name);
    }

    @PutMapping()
    public void add(@RequestParam final String name, @RequestParam final List<String> roles) {
        LOG.debug("Add a node request");
        nodeService.add(name, roles);
    }

    @DeleteMapping("/{name}")
    public void remove(@PathVariable final String name) {
        LOG.debug("Remove a node request");
        nodeService.remove(name);
    }
}
