package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.dto.ServiceDto;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.translators.ServiceDtoTranslator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ServiceTranslatorTest {

    private Service service;

    private ServiceDto dto;

    @Before
    public void init() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        dto = new ServiceDto();
        dto.setName("1");
        dto.setImage("docker-registry.cloud.sophos/haproxy:dev@sha256:123abc");
        dto.setRoles(rolesString);
        dto.setVolumes(new HashSet<>());
        dto.setPorts(new HashSet<>());

        service = new Service();
        service.setName("1");
        service.setImage(new ImageReference("docker-registry.cloud.sophos/haproxy:dev@sha256:123abc"));
        service.setRoles(roles);
        service.setVolumes(new HashSet<>());
        service.setPorts(new HashSet<>());
    }

    @Test
    public void testGetDto() {
        ServiceDto serviceDto = new ServiceDtoTranslator().getDto(service);

        assertEquals(dto.getName(), serviceDto.getName());
        assertEquals(dto.getRoles(), serviceDto.getRoles());
    }

    @Test
    public void testFromDto() {
        Service serviceActual = new ServiceDtoTranslator().fromDto(dto);

        assertEquals(service.getName(), serviceActual.getName());
        assertEquals(service.getRoles(), serviceActual.getRoles());
    }
}
