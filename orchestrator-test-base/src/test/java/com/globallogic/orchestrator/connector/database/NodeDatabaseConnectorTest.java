package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.dao.database.mapper.NodeRowMapper;
import com.globallogic.orchestrator.dao.dto.NodeDto;
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

public class NodeDatabaseConnectorTest {

    @InjectMocks
    private NodeDatabaseConnectorImpl nodeDatabaseConnector;

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

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);

        doAnswer(invocation -> null).when(jdbcTemplate).update(anyString());

        nodeDatabaseConnector.insert(nodeDto.getName(), nodeDto.getRoles());
    }

    @Test
    public void getAll() {
        Set<NodeDto> nodeDtos = new HashSet<>();

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

        NodeRowMapper mapper = new NodeRowMapper();
        when(jdbcTemplate.query(anyString(), eq(mapper))).thenReturn(new ArrayList<>(nodeDtos));

        assertEquals(nodeDtos, nodeDatabaseConnector.getAll(mapper));
    }

    @Test
    public void getByName() {
        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);

        List<NodeDto> nodes = new ArrayList<>();
        nodes.add(nodeDto);

        NodeRowMapper mapper = new NodeRowMapper();
        when(jdbcTemplate.query(anyString(), eq(mapper), anyString())).thenReturn(nodes);

        assertEquals(nodeDto, nodeDatabaseConnector.getByName("1", mapper));
    }
}