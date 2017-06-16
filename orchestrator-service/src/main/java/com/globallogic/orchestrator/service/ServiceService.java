package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Service;

import java.util.Set;

public interface ServiceService {
    void write(String fileName, Set<Service> services);

    Set<Service> read(String fileName);
}
