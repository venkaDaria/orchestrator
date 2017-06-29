package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void remove(@RequestBody final String name) {
        serviceService.remove(name);
    }

    @RequestMapping("/add")
    public void add() {
        Service s = new Service();
        s.setName("one");
        ImageReference ref = new ImageReference("docker-registry.cloud.sophos/haproxy:dev");
        s.setImage(ref);
        s.setPorts(new HashSet<>());
        s.setVolumes(new HashSet<>());

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));
        s.setRoles(roles);

        Service s2 = s.copy();
        s2.setName("two");

        Set<Service> set = new HashSet<>();
        set.add(s);
        set.add(s2);
        serviceService.save(set);
    }
}

