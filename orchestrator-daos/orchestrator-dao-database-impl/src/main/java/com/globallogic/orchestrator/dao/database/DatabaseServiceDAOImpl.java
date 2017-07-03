package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnector;
import com.globallogic.orchestrator.dao.database.mapper.ServiceRowMapper;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DatabaseServiceDAOImpl implements DatabaseServiceDAO {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseServiceDAOImpl.class);

    @Autowired
    private ServiceDatabaseConnector connector;

    @Autowired
    private ServiceRowMapper mapper;

    @Override
    @Transactional
    public void save(final Set<ServiceDto> services) {
        LOG.debug("Save services -> " + services);
        services.forEach(el -> connector.insert(el.getName(), el.getImage(),el.getRoles(), el.getPorts(), el.getVolumes()));
    }

    @Override
    public Set<ServiceDto> load() {
        LOG.debug("Load services");
        return connector.getAll(mapper);
    }

    @Override
    public ServiceDto getByName(final String name) {
        LOG.debug("Get a service by name -> " + name);
        return connector.getByName(name, mapper);
    }

    @Override
    public void remove(final String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final String name, final String image, final List<String> roles, final List<String> ports, final List<String> volumes) {
        LOG.debug("Insert a service");
        connector.insert(name, image, new HashSet<>(roles), new HashSet<>(ports), new HashSet<>(volumes));
    }
}
