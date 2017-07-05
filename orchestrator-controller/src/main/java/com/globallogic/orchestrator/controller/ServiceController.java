package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ServiceService serviceService;

    @GetMapping()
    public Set<Service> getAll() {
        LOG.debug("Get all services request");
        return serviceService.load();
    }

    @GetMapping("/{name}")
    public Service getService(@PathVariable final String name) {
        LOG.debug("Get a service by id request");
        return serviceService.getByName(name);
    }

    @PutMapping()
    public void add(@RequestParam final String name, @RequestParam final String image,
                    @RequestParam final List<String> roles, @RequestParam final List<String> ports,
                    @RequestParam final List<String> volumes) {
        LOG.debug("Add a service request");
        serviceService.add(name, image, roles, ports, volumes);
    }

    @DeleteMapping("/{name}")
    public void remove(@PathVariable final String name) {
        LOG.debug("Remove a service request");
        serviceService.remove(name);
    }
}

