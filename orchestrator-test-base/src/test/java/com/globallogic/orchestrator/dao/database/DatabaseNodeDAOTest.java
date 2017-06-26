package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.NodeDatabaseConnectorImpl;
import com.globallogic.orchestrator.dao.database.mapper.NodeRowMapper;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class DatabaseNodeDAOTest {

    @InjectMocks
    private DatabaseNodeDAOImpl databaseNodeDAO;

    @Mock
    private NodeDatabaseConnectorImpl connector;

    @Mock
    private NodeRowMapper mapper;
    
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);

        when(connector.getByName("1", mapper)).thenReturn(nodeDto);

        assertEquals(nodeDto, databaseNodeDAO.getByName("1"));
    }

    @Test
    public void testLoad() {
        Set<NodeDto> nodeDtos = new LinkedHashSet<>();

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);
        nodeDtos.add(nodeDto);

        NodeDto nodeDto2 = new NodeDto();
        nodeDto2.setName("2");
        nodeDto2.setRoles(rolesString);
        nodeDtos.add(nodeDto2);

        when(connector.getAll(mapper)).thenReturn(nodeDtos);

        assertEquals(nodeDtos, databaseNodeDAO.load());
    }

    @Test
    public void testSave() {
        HashSet<NodeDto> nodeDtos = new HashSet<>();

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);
        nodeDtos.add(nodeDto);

        NodeDto nodeDto2 = new NodeDto();
        nodeDto2.setName("2");
        nodeDto2.setRoles(rolesString);
        nodeDtos.add(nodeDto2);

        doAnswer(invocation -> null).when(connector).insert(nodeDto.getName(), nodeDto.getRoles());
        doAnswer(invocation -> null).when(connector).insert(nodeDto2.getName(), nodeDto2.getRoles());

        databaseNodeDAO.save(nodeDtos);
    }
}
