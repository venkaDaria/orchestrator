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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Set<String> getAll() {
        return containerService.load().stream().map(Container::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getContainer(@PathVariable final String id) {
        return containerService.getById(id).asFormattedString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@RequestBody final String id) {
        containerService.remove(id);
    }
}
