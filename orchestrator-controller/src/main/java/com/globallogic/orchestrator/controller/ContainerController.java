package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.ContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/container", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContainerController {

    private static final Logger LOG = LoggerFactory.getLogger(ContainerController.class);

    @Autowired
    private ContainerService containerService;

    @GetMapping()
    public Set<Container> getAll() {
        LOG.debug("Get all containers request");
        return containerService.load();
    }

    @GetMapping("/{id}")
    public Container getContainer(@PathVariable final String id) {
        LOG.debug("Get a container by id request");
        return containerService.getById(id);
    }

    @PutMapping()
    public void add(@RequestParam final String id, @RequestParam final String status,
                    @RequestParam final String node, @RequestParam final String server) {
        LOG.debug("Add a container request");
        containerService.add(id, status, node, server);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable final String id) {
        LOG.debug("Remove a container request");
        containerService.remove(id);
    }
}
