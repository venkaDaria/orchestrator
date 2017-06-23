package com.globallogic.orchestrator.dao.database.mapper;

import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class ServiceRowMapper implements RowMapper<ServiceDto> {

    @Override
    public ServiceDto mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        ServiceDto service = new ServiceDto();

        service.setName(rs.getString(0));
        service.setImage(rs.getString(1));

        if (StringUtils.isNotEmpty(rs.getString(2))) {
            service.setRoles(new HashSet<>(Arrays.asList(rs.getString(2)
                    .split(SeparatorHolder.getSeparatorDatabaseString()))));
        } else {
            service.setRoles(new HashSet<>());
        }

        if (StringUtils.isNotEmpty(rs.getString(3))) {
            service.setPorts(new HashSet<>(Arrays.asList(rs.getString(3)
                    .split(SeparatorHolder.getSeparatorDatabaseString()))));
        } else {
            service.setPorts(new HashSet<>());
        }

        if (StringUtils.isNotEmpty(rs.getString(4))) {
            service.setVolumes(new HashSet<>(Arrays.asList(rs.getString(4)
                    .split(SeparatorHolder.getSeparatorDatabaseString()))));
        } else {
            service.setVolumes(new HashSet<>());
        }

        return service;
    }
}
