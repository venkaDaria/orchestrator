package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.LocaleSeparator;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.ConfigurationServiceImpl;
import com.globallogic.orchestrator.service.interfaces.ConfigurationService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private Configuration config;

    @ModelAttribute("service")
    public void common() {
        SeparatorHolder.setSeparator(LocaleSeparator.COMMA);
        ConfigurationService cs = new ConfigurationServiceImpl(DAOType.FILE_SYSTEM);
        config = cs.load();
    }

    @RequestMapping("")
    public Set<Service> getAll() {
        return config.getServices();
    }

    @RequestMapping("/{id}")
    public Service getContainer(@PathVariable String name) {
        for (Service service : config.getServices()) {
            if (service.getName().equals(name))
                return service;
        }
        return null;
    }
}
