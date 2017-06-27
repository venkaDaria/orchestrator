package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnectorImpl;
import com.globallogic.orchestrator.dao.database.mapper.ServiceRowMapper;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
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

public class DatabaseServiceDAOTest {

    @InjectMocks
    private DatabaseServiceDAOImpl databaseServiceDAO;

    @Mock
    private ServiceDatabaseConnectorImpl connector;

    @Mock
    private ServiceRowMapper mapper;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);

        when(connector.getByName("1", mapper)).thenReturn(serviceDto);

        assertEquals(serviceDto, databaseServiceDAO.getByName("1"));
    }

    @Test
    public void testLoad() {
        Set<ServiceDto> serviceDtos = new HashSet<>();

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);
        serviceDtos.add(serviceDto);

        ServiceDto serviceDto2 = new ServiceDto();
        serviceDto2.setName("2");
        serviceDto2.setRoles(rolesString);
        serviceDtos.add(serviceDto2);

        when(connector.getAll(mapper)).thenReturn(serviceDtos);

        assertEquals(serviceDtos, databaseServiceDAO.load());
    }

    @Test
    public void testSave() {
        HashSet<ServiceDto> serviceDtos = new HashSet<>();

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);
        serviceDtos.add(serviceDto);

        ServiceDto serviceDto2 = new ServiceDto();
        serviceDto2.setName("2");
        serviceDto2.setRoles(rolesString);
        serviceDtos.add(serviceDto2);

        doAnswer(invocation -> null).when(connector).insert(serviceDto.getName(), serviceDto.getImage(),
                serviceDto.getRoles(), serviceDto.getPorts(), serviceDto.getVolumes());
        doAnswer(invocation -> null).when(connector).insert(serviceDto2.getName(), serviceDto2.getImage(),
                serviceDto2.getRoles(), serviceDto2.getPorts(), serviceDto2.getVolumes());

        databaseServiceDAO.save(serviceDtos);
    }
}