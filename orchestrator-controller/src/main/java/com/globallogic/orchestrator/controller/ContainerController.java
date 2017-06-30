package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/container", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @GetMapping()
    public Set<Container> getAll() {
        return containerService.load();
    }

    @GetMapping("/{id}")
    public Container getContainer(@PathVariable final String id) {
        return containerService.getById(id);
    }

    @PutMapping()
    public void add(@RequestParam final String id, @RequestParam final String status,
                    @RequestParam final String node, @RequestParam final String server) {
        containerService.add(id, status, node, server);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable final String id) {
        containerService.remove(id);
    }
}
