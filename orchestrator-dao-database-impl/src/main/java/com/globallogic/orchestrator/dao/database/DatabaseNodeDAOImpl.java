package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.NodeDatabaseConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DatabaseNodeDAOImpl implements DatabaseNodeDAO {

    @Autowired
    private NodeDatabaseConnector connector;

    @Override
    @Transactional
    public void save(final Set<NodeDto> nodes) {
        nodes.forEach(el -> connector.insert(el.getName(), getString(el.getRoles())));
    }

    private String getString(Set<String> roles) {
        StringBuilder sb = new StringBuilder();
        roles.forEach(role -> sb.append(roles).append(SeparatorHolder.getSeparatorDatabaseString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    @Override
    public Set<NodeDto> load() {
        return connector.getAll().stream().map(this::extract).collect(Collectors.toSet());
    }

    @Override
    public NodeDto getByName(String name) {
        return extract(connector.getByName(name));
    }

    private NodeDto extract(final String... params) {
        NodeDto node = new NodeDto();

        node.setName(params[0]);
        if (StringUtils.isNotEmpty(params[1])) {
            node.setRoles(new HashSet<>(Arrays.asList(params[1].split(SeparatorHolder.getSeparatorDatabaseString()))));
        } else {
            node.setRoles(new HashSet<>());
        }

        return node;
    }
}
