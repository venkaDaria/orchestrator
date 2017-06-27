package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnector;
import com.globallogic.orchestrator.dao.database.mapper.ServiceRowMapper;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class DatabaseServiceDAOImpl implements DatabaseServiceDAO {

    @Autowired
    private ServiceDatabaseConnector connector;

    @Autowired
    private ServiceRowMapper mapper;

    @Override
    @Transactional
    public void save(final Set<ServiceDto> services) {
        services.forEach(el -> connector.insert(el.getName(), el.getImage(),el.getRoles(), el.getPorts(), el.getVolumes()));
    }

    @Override
    public Set<ServiceDto> load() {
        return connector.getAll(mapper);
    }

    @Override
    public ServiceDto getByName(final String name) {
        return connector.getByName(name, mapper);
    }
}
