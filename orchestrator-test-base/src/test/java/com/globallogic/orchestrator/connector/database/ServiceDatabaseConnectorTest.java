package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.dao.database.mapper.ServiceRowMapper;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class ServiceDatabaseConnectorTest {

    @InjectMocks
    private ServiceDatabaseConnectorImpl serviceDatabaseConnector;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insert() {
        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);
        serviceDto.setVolumes(new HashSet<>());
        serviceDto.setPorts(new HashSet<>());

        doAnswer(invocation -> null).when(jdbcTemplate).update(anyString());

        serviceDatabaseConnector.insert(serviceDto.getName(), serviceDto.getImage(),
                serviceDto.getRoles(), serviceDto.getPorts(), serviceDto.getVolumes());
    }

    @Test
    public void getAll() {
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

        ServiceRowMapper mapper = new ServiceRowMapper();
        when(jdbcTemplate.query(anyString(), eq(mapper))).thenReturn(new ArrayList<>(serviceDtos));

        assertEquals(serviceDtos, serviceDatabaseConnector.getAll(mapper));
    }

    @Test
    public void getByName() {
        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);

        List<ServiceDto> services = new ArrayList<>();
        services.add(serviceDto);

        ServiceRowMapper mapper = new ServiceRowMapper();
        when(jdbcTemplate.query(anyString(), eq(mapper), anyString())).thenReturn(services);

        assertEquals(serviceDto, serviceDatabaseConnector.getByName("1", mapper));
    }
}
