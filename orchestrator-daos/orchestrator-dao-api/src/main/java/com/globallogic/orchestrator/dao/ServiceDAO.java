package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.ServiceDto;

import java.util.List;
import java.util.Set;

public interface ServiceDAO {

    void save(final Set<ServiceDto> services);

    Set<ServiceDto> load();

    ServiceDto getByName(final String name);

    void remove(String name);

    void add(String name, String image, List<String> roles, List<String> ports, List<String> volumes);
}
