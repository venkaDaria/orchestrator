package com.globallogic.orchestrator.dao.database.mapper;

import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class NodeRowMapper implements RowMapper<NodeDto> {

    @Override
    public NodeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        NodeDto node = new NodeDto();

        node.setName(rs.getString(0));
        if (StringUtils.isNotEmpty(rs.getString(1))) {
            node.setRoles(new HashSet<>(Arrays.asList(rs.getString(1)
                    .split(SeparatorHolder.getSeparatorDatabaseString()))));
        } else {
            node.setRoles(new HashSet<>());
        }

        return node;
    }
}
