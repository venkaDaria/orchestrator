package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orchestrator/container")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @GetMapping()
    public Set<String> getAll() {
        return containerService.load().stream().map(Container::asFormattedString).collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public String getContainer(@PathVariable final String id) {
        return containerService.getById(id).asFormattedString();
    }

    @PutMapping(value = "/",  params = { "id", "status", "node", "server" })
    public void add(@RequestParam final String id, @RequestParam final String status,
                    @RequestParam final String node, @RequestParam final String server) {
        containerService.add(id, status, node, server);
    }

    @DeleteMapping("/{id}")
    public void remove(@RequestParam final String id) {
        containerService.remove(id);
    }
}
