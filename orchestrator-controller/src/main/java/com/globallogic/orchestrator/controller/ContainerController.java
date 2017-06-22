package com.globallogic.orchestrator.controller;

import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.LocaleSeparator;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.model.base.NodeBase;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.ConfigurationServiceImpl;
import com.globallogic.orchestrator.service.interfaces.ConfigurationService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.globallogic.orchestrator.model.entity.Container;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/container")
public class ContainerController {

    private Configuration config;

    @ModelAttribute("container")
    public void common() {
        SeparatorHolder.setSeparator(LocaleSeparator.COMMA);
        ConfigurationService cs = new ConfigurationServiceImpl(DAOType.FILE_SYSTEM);
        config = cs.load();
    }

    @RequestMapping("")
    public Set<String> getAll() {
        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));
        return containers.stream().map(Container::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{id}")
    public String getContainer(@PathVariable String id) {
        Container cont = null;
        for (Node node : config.getNodes()) {
            cont = node.getContainers().stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
            if (cont != null)
                break;
        }
        return cont.asFormattedString();
    }
}
