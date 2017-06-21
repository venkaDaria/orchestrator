package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ServiceDTO;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Port;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.model.valueobject.Volume;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ServiceTranslatorImpl implements Translator<Service, ServiceDTO> {
    private final String SEPARATOR;

    public ServiceTranslatorImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public ServiceDTO getDto(Service model) {
        ServiceDTO dto = new ServiceDTO();
        dto.setName(model.getName());
        dto.setImage(model.getImage().getValue());

        StringBuilder sb = new StringBuilder();
        model.getRoles().forEach(role -> sb.append(role.getValue()).append(SEPARATOR));
        dto.setRoles(removeLastChar(sb));

        StringBuilder sb2 = new StringBuilder();
        model.getPorts().forEach(port -> sb2.append(port.getValue()).append(SEPARATOR));
        dto.setPorts(removeLastChar(sb2));

        StringBuilder sb3 = new StringBuilder();
        model.getVolumes().forEach(volume -> sb3.append(volume.getValue()).append(SEPARATOR));
        dto.setVolumes(removeLastChar(sb3));

        return dto;
    }

    private String removeLastChar(StringBuilder sb) {
        String str = sb.toString();
        return (StringUtils.isNotEmpty(str)) ? str.substring(0, str.length() - 1) : str;
    }

    @Override
    public Service fromDto(ServiceDTO dto) {
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
