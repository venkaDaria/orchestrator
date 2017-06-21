package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ServiceDTO;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Port;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.model.valueobject.Volume;
import com.globallogic.orchestrator.service.interfaces.ServiceService;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ServiceServiceImpl implements ServiceService {
    private DAOType type;
    private final String SEPARATOR;

    public ServiceServiceImpl(final DAOType type) {
        this.type = type;
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public void save(final Set<Service> services) {
        Set<ServiceDTO> set = new HashSet<>();
        services.forEach(service -> set.add(getDTO(service)));

        DAOFactory.getInstance(type).getServiceDAO().save(set);
    }

    @Override
    public Set<Service> load() {
        Set<ServiceDTO> set = DAOFactory.getInstance(type).getServiceDAO().load();

        Set<Service> services = new HashSet<>();
        set.forEach(dto -> services.add(getService(dto)));

        return services;
    }

    private ServiceDTO getDTO(final Service service) {
        ServiceDTO dto = new ServiceDTO();
        dto.setName(service.getName());
        dto.setImage(service.getImage().getValue());

        StringBuilder sb = new StringBuilder();
        service.getRoles().forEach(role -> sb.append(role.getValue()).append(SEPARATOR));
        dto.setRoles(removeLastChar(sb));

        StringBuilder sb2 = new StringBuilder();
        service.getPorts().forEach(port -> sb2.append(port.getValue()).append(SEPARATOR));
        dto.setPorts(removeLastChar(sb2));

        StringBuilder sb3 = new StringBuilder();
        service.getVolumes().forEach(volume -> sb3.append(volume.getValue()).append(SEPARATOR));
        dto.setVolumes(removeLastChar(sb3));

        return dto;
    }

    private String removeLastChar(StringBuilder sb) {
        String str = sb.toString();
        return (StringUtils.isNotEmpty(str)) ? str.substring(0, str.length() - 1) : str;
    }

    private Service getService(final ServiceDTO dto) {
        Service service = new Service();
        service.setName(dto.getName());
        service.setImage(new ImageReference(dto.getImage()));

        Set<Role> roles = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getRoles())) {
            Arrays.stream(dto.getRoles().split(SEPARATOR)).forEach(role -> roles.add(new Role(role)));
        }
        service.setRoles(roles);

        Set<Port> ports = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getPorts())) {
            Arrays.stream(dto.getPorts().split(SEPARATOR)).forEach(port -> ports.add(new Port(port)));
        }
        service.setPorts(ports);

        Set<Volume> volumes = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getVolumes())) {
            Arrays.stream(dto.getVolumes().split(SEPARATOR)).forEach(volume -> volumes.add(new Volume(volume)));
        }
        service.setVolumes(volumes);

        return service;
    }
}
