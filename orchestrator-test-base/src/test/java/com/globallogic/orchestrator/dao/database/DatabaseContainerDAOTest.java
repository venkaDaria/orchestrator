package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ContainerDatabaseConnectorImpl;
import com.globallogic.orchestrator.dao.database.mapper.ContainerRowMapper;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
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

public class DatabaseContainerDAOTest {

    @InjectMocks
    private DatabaseContainerDAOImpl databaseContainerDAO;

    @Mock
    private ContainerDatabaseConnectorImpl connector;

    @Mock
    private ContainerRowMapper mapper;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");
        contDto.setStatus("NONE");
        contDto.setServiceName("service");
        contDto.setNodeName("node");

        when(connector.getById("1", mapper)).thenReturn(contDto);

        assertEquals(contDto, databaseContainerDAO.getById("1"));
    }

    @Test
    public void testLoad() {
        Set<ContainerDto> containerDtos = new HashSet<>();

        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");
        contDto.setStatus("NONE");
        containerDtos.add(contDto);

        ContainerDto contDto2 = new ContainerDto();
        contDto2.setId("2");
        contDto2.setStatus("NONE");
        containerDtos.add(contDto2);

        when(connector.getAll(mapper)).thenReturn(containerDtos);

        assertEquals(containerDtos, databaseContainerDAO.load());
    }

    @Test
    public void testSave() {
        HashSet<ContainerDto> containerDtos = new HashSet<>();

        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");
        containerDtos.add(contDto);

        ContainerDto contDto2 = new ContainerDto();
        contDto2.setId("2");
        containerDtos.add(contDto2);

        doAnswer(invocation -> null).when(connector).insert(contDto.getId(), contDto.getStatus(),
                contDto.getNodeName(), contDto.getServiceName());
        doAnswer(invocation -> null).when(connector).insert(contDto2.getId(), contDto2.getStatus(),
                contDto2.getNodeName(), contDto2.getServiceName());

        databaseContainerDAO.save(containerDtos);
    }
}
