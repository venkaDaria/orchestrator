package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.LocaleSeparator;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.model.base.NodeBase;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.ConfigurationServiceImpl;
import com.globallogic.orchestrator.service.interfaces.ConfigurationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.globallogic.orchestrator.model.entity.Container;

import java.util.HashSet;
import java.util.Set;

@RestController
public class ContainerController {

    @RequestMapping("/")
    public String index() {
        return "Hello, world!";
    }

    @RequestMapping("/container")
    public Set<Container> getAll() {
        SeparatorHolder.setSeparator(LocaleSeparator.COMMA);
        ConfigurationService cs = new ConfigurationServiceImpl(DAOType.FILE_SYSTEM);
        Configuration config = cs.load();

        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));
        return containers;
    }

    @RequestMapping("/container/{id}")
    public Container getContainer(@PathVariable String id) {
        SeparatorHolder.setSeparator(LocaleSeparator.COMMA);
        ConfigurationService cs = new ConfigurationServiceImpl(DAOType.FILE_SYSTEM);
        Configuration config = cs.load();

        Container cont = null;
        for (Node node : config.getNodes()) {
            cont = node.getContainers().stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
            break;
        }
        return cont;
    }
}
