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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/node")
public class NodeController {

    private Configuration config;

    @ModelAttribute("node")
    public void common() {
        SeparatorHolder.setSeparator(LocaleSeparator.COMMA);
        ConfigurationService cs = new ConfigurationServiceImpl(DAOType.FILE_SYSTEM);
        config = cs.load();
    }

    @RequestMapping("")
    public Set<String> getAll() {
        return config.getNodes().stream().map(Node::asFormattedString).collect(Collectors.toSet());
    }

    @RequestMapping("/{name}")
    public String getContainer(@PathVariable String name) {
        for (Node node : config.getNodes()) {
            if (node.getName().equals(name))
                return node.asFormattedString();
        }
        return null;
    }
}
