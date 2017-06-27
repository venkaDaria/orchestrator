package com.globallogic.orchestrator.dao.database.mapper;

import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class NodeRowMapper implements RowMapper<NodeDto> {

    private final Map<String, NodeDto> nodeMap;

    public NodeRowMapper() {
        nodeMap = new HashMap<>();
    }

    @Override
    public NodeDto mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        String name = rs.getString(1);

        NodeDto nodeDto = nodeMap.get(name) != null ? nodeMap.get(name) : new NodeDto();
        nodeDto.setName(name);
        Set<String> roles = nodeDto.getRoles() != null ? nodeDto.getRoles() : new HashSet<>();

        if (rs.getString(2) != null) {
            roles.add(rs.getString(2));
        }
        nodeDto.setRoles(roles);

        nodeMap.put(name, nodeDto);

        return nodeDto;
    }
}
