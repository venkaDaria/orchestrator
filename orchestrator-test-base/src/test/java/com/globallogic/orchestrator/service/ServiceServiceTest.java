package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.database.DatabaseServiceDAO;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.translators.ServiceDtoTranslator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class ServiceServiceTest {

    @InjectMocks
    private ServiceServiceImpl serviceService;

    @Mock
    private DatabaseServiceDAO serviceDao;

    @Mock
    private ServiceDtoTranslator translator;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByName() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);

        Service service = new Service();
        service.setName("1");
        service.setRoles(roles);

        when(serviceDao.getByName("1")).thenReturn(serviceDto);
        when(translator.fromDto(serviceDto)).thenReturn(service);

        assertEquals(service, serviceService.getByName("1"));
    }

    @Test
    public void testSave() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        HashSet<Service> services = new HashSet<>();

        Service service = new Service();
        service.setName("1");
        service.setRoles(roles);
        services.add(service);

        Service service2 = new Service();
        service2.setName("2");
        services.add(service2);

        HashSet<ServiceDto> serviceDtos = new HashSet<>();

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);
        serviceDtos.add(serviceDto);

        ServiceDto serviceDto2 = new ServiceDto();
        serviceDto2.setName("2");
        serviceDtos.add(serviceDto2);

        doAnswer(invocation -> null).when(serviceDao).save(serviceDtos);
        when(translator.getDto(service)).thenReturn(serviceDto);
        when(translator.getDto(service2)).thenReturn(serviceDto2);

        serviceService.save(services);
    }

    @Test
    public void testLoad() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        HashSet<ServiceDto> serviceDtos = new HashSet<>();

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);
        serviceDtos.add(serviceDto);

        ServiceDto serviceDto2 = new ServiceDto();
        serviceDto2.setName("2");
        serviceDtos.add(serviceDto2);

        HashSet<Service> services = new HashSet<>();

        Service service = new Service();
        service.setName("1");
        service.setRoles(roles);
        services.add(service);

        Service service2 = new Service();
        service2.setName("2");
        services.add(service2);

        when(serviceDao.load()).thenReturn(serviceDtos);
        when(translator.fromDto(serviceDto)).thenReturn(service);
        when(translator.fromDto(serviceDto2)).thenReturn(service2);

        assertEquals(services, serviceService.load());
    }
}
