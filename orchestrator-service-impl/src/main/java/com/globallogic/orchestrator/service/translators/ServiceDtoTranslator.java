package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import com.globallogic.orchestrator.model.StringValueObject;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Port;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.model.valueobject.Volume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceDtoTranslator implements Translator<Service, ServiceDto> {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceDtoTranslator.class);

    @Override
    public ServiceDto getDto(final Service model) {
        LOG.debug("Get ServiceDto from -> " + model);
        ServiceDto dto = new ServiceDto();

        dto.setName(model.getName());
        dto.setImage(model.getImage().asString());

        dto.setRoles(model.getRoles().stream().map(StringValueObject::asString).collect(Collectors.toSet()));
        dto.setPorts(model.getPorts().stream().map(StringValueObject::asString).collect(Collectors.toSet()));
        dto.setVolumes(model.getVolumes().stream().map(StringValueObject::asString).collect(Collectors.toSet()));

        LOG.debug("Return ServiceDto -> " + dto);
        return dto;
    }

    @Override
    public Service fromDto(final ServiceDto dto) {
        LOG.debug("Get Service from -> " + dto);
        Service service = new Service();

        service.setName(dto.getName());
        service.setImage(new ImageReference(dto.getImage()));

        service.setRoles(dto.getRoles().stream().map(Role::new).collect(Collectors.toSet()));
        service.setPorts(dto.getPorts().stream().map(Port::new).collect(Collectors.toSet()));
        service.setVolumes(dto.getVolumes().stream().map(Volume::new).collect(Collectors.toSet()));

        LOG.debug("Return Service -> " + service);
        return service;
    }
}
