package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/orchestrator/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Set<String> getAll() {
        return serviceService.load().stream().map(Service::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getService(@PathVariable final String name) {
        return serviceService.getByName(name).asFormattedString();
    }

    @PutMapping(value = "/",  params = { "name", "image", "role", "port", "volume" })
    public void add(@RequestParam final String name, @RequestParam final String image,
                    @RequestParam final List<String> roles, @RequestParam final List<String> ports,
                    @RequestParam final List<String> volumes) {
        serviceService.add(name, image, roles, ports, volumes);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void remove(@RequestBody final String name) {
        serviceService.remove(name);
    }
}

