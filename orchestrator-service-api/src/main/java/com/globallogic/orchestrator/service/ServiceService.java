package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Service;

import java.util.List;
import java.util.Set;

public interface ServiceService {

    void save(final Set<Service> services);

    Set<Service> load();

    Service getByName(final String name);

    void remove(String name);

    void add(String name, String image, List<String> roles, List<String> ports, List<String> volumes);
}
