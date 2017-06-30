package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping()
    public Set<Service> getAll() {
        return serviceService.load();
    }

    @GetMapping("/{name}")
    public Service getService(@PathVariable final String name) {
        return serviceService.getByName(name);
    }

    @PutMapping()
    public void add(@RequestParam final String name, @RequestParam final String image,
                    @RequestParam final List<String> roles, @RequestParam final List<String> ports,
                    @RequestParam final List<String> volumes) {
        serviceService.add(name, image, roles, ports, volumes);
    }

    @DeleteMapping("/{name}")
    public void remove(@PathVariable final String name) {
        serviceService.remove(name);
    }
}

