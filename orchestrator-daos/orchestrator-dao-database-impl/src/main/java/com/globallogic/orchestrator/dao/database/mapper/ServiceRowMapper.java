package com.globallogic.orchestrator.dao.database.mapper;

import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ServiceRowMapper implements RowMapper<ServiceDto> {

    private final Map<String, ServiceDto> serviceMap;

    public ServiceRowMapper() {
        serviceMap = new HashMap<>();
    }

    @Override
    public ServiceDto mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        String name = rs.getString(1);
        ServiceDto serviceDto = serviceMap.get(name) != null ? serviceMap.get(name) : new ServiceDto();
        serviceDto.setName(name);
        serviceDto.setImage(rs.getString(2));

        Set<String> roles = serviceDto.getRoles() != null ? serviceDto.getRoles() : new HashSet<>();
        if (rs.getString(3) != null) {
            roles.add(rs.getString(3));
        }
        serviceDto.setRoles(roles);

        Set<String> ports = serviceDto.getPorts() != null ? serviceDto.getPorts() : new HashSet<>();
        if (rs.getString(4) != null) {
            ports.add(rs.getString(4));
        }
        serviceDto.setPorts(ports);

        Set<String> volumes = serviceDto.getVolumes() != null ? serviceDto.getVolumes() : new HashSet<>();
        if (rs.getString(5) != null) {
            volumes.add(rs.getString(5));
        }
        serviceDto.setVolumes(volumes);

        serviceMap.put(name, serviceDto);

        return serviceDto;
    }
}
