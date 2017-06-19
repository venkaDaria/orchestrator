package com.globallogic.orchestrator.dao.db;

import com.globallogic.orchestrator.connector.db.ServiceDbConnector;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dto.ServiceDTO;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Port;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.model.valueobject.Volume;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DbServiceDAO implements ServiceDAO {
    private static final String SEP = ";";

    @Override
    public void save(Set<Service> services) {
        Set<ServiceDTO> set = new HashSet<>();
        services.forEach(service -> set.add(getDTO(service)));

        new ServiceDbConnector().insert(set);
    }

    private static ServiceDTO getDTO(Service service) {
        ServiceDTO dto = new ServiceDTO();
        dto.setName(service.getName());
        dto.setImage(service.getImage().getValue());

        StringBuilder sb = new StringBuilder();
        service.getRoles().forEach(role -> sb.append(role.getValue()).append(SEP));
        dto.setRoles(sb.toString());

        StringBuilder sb2= new StringBuilder();
        service.getPorts().forEach(port -> sb2.append(port.getValue()).append(SEP));
        dto.setPorts(sb2.toString());

        StringBuilder sb3 = new StringBuilder();
        service.getVolumes().forEach(volume -> sb3.append(volume.getValue()).append(SEP));
        dto.setVolumes(sb3.toString());

        return dto;
    }

    @Override
    public Set<Service> load() {
        Set<ServiceDTO> set = new ServiceDbConnector().getAll();

        Set<Service> services = new HashSet<>();
        set.forEach(dto -> services.add(getService(dto)));
        return services;
    }

    private Service getService(ServiceDTO dto) {
        Service service = new Service();
        service.setName(dto.getName());
        service.setImage(new ImageReference(dto.getImage()));
        
        Set<Role> roles = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getRoles())) {
            Arrays.stream(dto.getRoles().split(SEP)).forEach(role -> roles.add(new Role(role)));
        }
        service.setRoles(roles);

        Set<Port> ports = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getPorts())) {
            Arrays.stream(dto.getPorts().split(SEP)).forEach(port -> ports.add(new Port(port)));
        }
        service.setPorts(ports);

        Set<Volume> volumes = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getVolumes())) {
            Arrays.stream(dto.getVolumes().split(SEP)).forEach(volume -> volumes.add(new Volume(volume)));
        }
        service.setVolumes(volumes);
        
        return service;
    }
}
