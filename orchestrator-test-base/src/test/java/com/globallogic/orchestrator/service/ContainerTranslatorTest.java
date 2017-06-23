package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ContainerTranslatorTest {

    private Container container;

    private ContainerDto dto;

    private Service service;

    private Node node;

    @Before
    public void init() {
        dto = new ContainerDto();

        dto.setId("1");
        dto.setNodeName("name");
        dto.setServiceName("service");
        dto.setStatus("ACTIVE");

        container = new Container();
        container.setId("1");
        container.setStatus(Status.ACTIVE);

        node = new Node();
        node.setName("name");

        service = new Service();
        service.setName("service");

        container.setNode(node);
        container.setService(service);
    }

    @Test
    public void testGetDto() {
        ContainerDto cont = new ContainerDtoTranslator().getDto(container);

        assertEquals(dto.getId(), cont.getId());
        assertEquals(dto.getStatus(), cont.getStatus());
        assertEquals(dto.getNodeName(), cont.getNodeName());
        assertEquals(dto.getServiceName(), cont.getServiceName());
    }

    @Test
    public void testFromDto() {
        Container cont = new ContainerDtoTranslator().fromDto(dto, node, service);

        assertEquals(container.getId(), cont.getId());
        assertEquals(container.getStatus(), cont.getStatus());
        assertEquals(container.getNode().getName(), cont.getNode().getName());
        assertEquals(container.getService().getName(), cont.getService().getName());
    }

    @Test
    public void testFromDto_NullNodeAndService() {
        container.setNode(null);
        container.setService(null);

        dto.setStatus("NONE");

        Container cont = new ContainerDtoTranslator().fromDto(dto);

        assertEquals(container.getId(), cont.getId());
        assertEquals(container.getStatus(), cont.getStatus());
        assertNull(cont.getNode());
        assertNull(cont.getService());
    }
}
