package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.translators.NodeDtoTranslator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class NodeTranslatorTest {

    private Node node;

    private NodeDto dto;

    @Before
    public void init() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        dto = new NodeDto();
        dto.setName("1");
        dto.setRoles(rolesString);

        node = new Node();
        node.setName("1");
        node.setRoles(roles);
    }

    @Test
    public void testGetDto() {
        NodeDto nodeDto = new NodeDtoTranslator().getDto(node);

        assertEquals(dto.getName(), nodeDto.getName());
        assertEquals(dto.getRoles(), nodeDto.getRoles());
    }

    @Test
    public void testFromDto() {
        Node nodeActual = new NodeDtoTranslator().fromDto(dto);

        assertEquals(node.getName(), nodeActual.getName());
        assertEquals(node.getRoles(), nodeActual.getRoles());
    }
}
