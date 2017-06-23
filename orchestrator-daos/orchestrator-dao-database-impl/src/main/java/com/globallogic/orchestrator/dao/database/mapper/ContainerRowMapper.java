package com.globallogic.orchestrator.dao.database.mapper;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContainerRowMapper implements RowMapper<ContainerDto> {

    @Override
    public ContainerDto mapRow(final ResultSet rs, final int rowNum) {
        ContainerDto container = new ContainerDto();

        try {
            container.setId(rs.getString(0));
            container.setStatus(rs.getString(1));
            container.setNodeName(rs.getString(2));
            container.setServiceName(rs.getString(3));
        } catch (SQLException e) {
            throw new DatabaseOperationException();
        }

        return container;
    }
}
