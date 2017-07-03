package com.globallogic.orchestrator.dao.database.mapper;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContainerRowMapper implements RowMapper<ContainerDto> {

    private static final Logger LOG = LoggerFactory.getLogger(ContainerRowMapper.class);

    @Override
    public ContainerDto mapRow(final ResultSet rs, final int rowNum) {
        ContainerDto container = new ContainerDto();

        try {
            container.setId(rs.getString(1));
            container.setStatus(rs.getString(2));
            container.setNodeName(rs.getString(3));
            container.setServiceName(rs.getString(4));
        } catch (SQLException e) {
            throw new DatabaseOperationException();
        }

        LOG.debug("Get ContainerDto -> " + container);
        return container;
    }
}
