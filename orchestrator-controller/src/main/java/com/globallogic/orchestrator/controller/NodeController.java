package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orchestrator/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Set<String> getAll() {
        return nodeService.load().stream().map(Node::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getContainer(@PathVariable final String name) {
        return nodeService.getByName(name).asFormattedString();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void remove(@RequestBody final String name) {
       nodeService.remove(name);
    }
}
