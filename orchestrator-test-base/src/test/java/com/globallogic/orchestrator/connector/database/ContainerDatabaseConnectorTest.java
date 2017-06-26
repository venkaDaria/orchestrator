package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.dao.database.mapper.ContainerRowMapper;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class ContainerDatabaseConnectorTest {

    @InjectMocks
    private ContainerDatabaseConnectorImpl containerDatabaseConnector;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insert() {
        ContainerDto containerDto = new ContainerDto();
        containerDto.setId("1");
        containerDto.setNodeName("name");
        containerDto.setServiceName("service");

        doAnswer(invocation -> null).when(jdbcTemplate).update(anyString(), anyString());

        containerDatabaseConnector.insert(new String[] { containerDto.getId(), containerDto.getStatus(),
                containerDto.getNodeName(), containerDto.getServiceName() });
    }

    @Test
    public void getAll() {
        Set<ContainerDto> containerDtos = new HashSet<>();

        ContainerDto containerDto = new ContainerDto();
        containerDto.setId("1");
        containerDto.setNodeName("name");
        containerDto.setServiceName("service");
        containerDtos.add(containerDto);

        ContainerDto containerDto2 = new ContainerDto();
        containerDto.setId("2");
        containerDto.setNodeName("name");
        containerDto.setServiceName("service");
        containerDtos.add(containerDto2);

        ContainerRowMapper mapper = new ContainerRowMapper();
        when(jdbcTemplate.query(anyString(), eq(mapper))).thenReturn(new ArrayList<>(containerDtos));

        assertEquals(containerDtos, containerDatabaseConnector.getAll(mapper));
    }

    @Test
    public void getById() {
        ContainerDto containerDto = new ContainerDto();
        containerDto.setId("1");
        containerDto.setNodeName("name");
        containerDto.setServiceName("service");

        List<ContainerDto> containers = new ArrayList<>();
        containers.add(containerDto);

        ContainerRowMapper mapper = new ContainerRowMapper();
        when(jdbcTemplate.query(anyString(), eq(mapper), anyString())).thenReturn(containers);

        assertEquals(containerDto, containerDatabaseConnector.getById("1", mapper));
    }
}
