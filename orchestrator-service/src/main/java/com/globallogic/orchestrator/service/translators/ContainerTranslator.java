package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;

public class ContainerTranslator implements Translator<Container, ContainerDTO> {
    @Override
    public ContainerDTO getDto(Container model) {
        ContainerDTO dto = new ContainerDTO();

        dto.setId(model.getId());
        dto.setNodeName(model.getNode().getName());
        dto.setServiceName(model.getService().getName());
        dto.setStatus(model.getStatus().toString());

        return dto;
    }

    @Override
    public Container fromDto(ContainerDTO dto) {
        return fromDto(dto, null, null);
    }

    public Container fromDto(ContainerDTO dto, Node node, Service service) {
        Container container = new Container();

        container.setId(dto.getId());
        container.setStatus(Status.valueOf(dto.getStatus()));

        container.setNode(node);
        container.setService(service);

        return container;
    }
}
