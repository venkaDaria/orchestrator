package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;

public class ContainerDtoTranslator implements Translator<Container, ContainerDto> {

    @Override
    public ContainerDto getDto(Container model) {
        ContainerDto dto = new ContainerDto();

        dto.setId(model.getId());
        dto.setNodeName(model.getNode().getName());
        dto.setServiceName(model.getService().getName());
        dto.setStatus(model.getStatus().toString());

        return dto;
    }

    @Override
    public Container fromDto(ContainerDto dto) {
        return fromDto(dto, null, null);
    }

    public Container fromDto(ContainerDto dto, Node node, Service service) {
        Container container = new Container();

        container.setId(dto.getId());
        container.setStatus(Status.valueOf(dto.getStatus()));

        container.setNode(node);
        container.setService(service);

        return container;
    }
}
