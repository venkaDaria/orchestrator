package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
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
        return connector.getAll(mapper);
    }

    @Override
    public ServiceDto getByName(String name) {
        return connector.getByName(name, mapper);
    }
}
