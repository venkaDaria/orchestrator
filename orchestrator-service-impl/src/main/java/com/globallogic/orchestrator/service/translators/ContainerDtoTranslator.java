package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Service
public class ContainerDtoTranslator implements Translator<Container, ContainerDto> {

    private static final Logger LOG = LoggerFactory.getLogger(ContainerDtoTranslator.class);

    @Override
    public ContainerDto getDto(final Container model) {
        LOG.debug("Get ContainerDto from -> " + model);
        ContainerDto dto = new ContainerDto();

        dto.setId(model.getId());
        dto.setNodeName(model.getNode().getName());
        dto.setServiceName(model.getService().getName());
        dto.setStatus(model.getStatus().toString());

        LOG.debug("Return ContainerDto -> " + dto);
        return dto;
    }

    @Override
    public Container fromDto(final ContainerDto dto) {
        return fromDto(dto, null, null);
    }

    public Container fromDto(final ContainerDto dto, final Node node, final Service service) {
        LOG.debug("Get Container from -> " + dto);
        Container container = new Container();

        container.setId(dto.getId());
        container.setStatus(Status.valueOf(dto.getStatus()));

        container.setNode(node);
        container.setService(service);

        LOG.debug("Return Container -> " + container);
        return container;
    }
}
