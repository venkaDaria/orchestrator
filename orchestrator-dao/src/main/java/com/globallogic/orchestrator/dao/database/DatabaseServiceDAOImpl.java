package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.impl.ServiceDatabaseConnectorImpl;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DatabaseServiceDAOImpl implements ServiceDAO {

    @Autowired
    private ServiceDatabaseConnectorImpl connector;

    @Override
    @Transactional
    public void save(final Set<ServiceDto> services) {
        services.forEach(el -> connector.insert(el.getName(), el.getImage(), getString(el.getRoles()),
            getString(el.getPorts()), getString(el.getVolumes())));
    }

    private String getString(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        set.forEach(el -> sb.append(el).append(SeparatorHolder.getSeparatorDatabaseString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    @Override
    public Set<ServiceDto> load() {
        return connector.getAll().stream().map(this::extract).collect(Collectors.toSet());
    }

    private ServiceDto extract(final String... params) {
        ServiceDto service = new ServiceDto();

        service.setName(params[0]);
        service.setImage(params[1]);

        service.setRoles(new HashSet<>(Arrays.asList(params[2].split(SeparatorHolder.getSeparatorDatabaseString()))));
        service.setPorts(new HashSet<>(Arrays.asList(params[3].split(SeparatorHolder.getSeparatorDatabaseString()))));
        service.setVolumes(new HashSet<>(Arrays.asList(params[4].split(SeparatorHolder.getSeparatorDatabaseString()))));

        return service;
    }
}
