package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class FileSystemNodeDAOTest {

    @InjectMocks
    private FileSystemNodeDAOImpl fileSystemNodeDAO;

    @Mock
    private FileSystemConnector connector;

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

        when(connector.read(anyString())).thenReturn(getString(nodeDto));

        assertEquals(nodeDto.toString(), fileSystemNodeDAO.getByName("1").toString());
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

        when(connector.read(anyString())).thenReturn(getString(nodeDto) + getString(nodeDto2));

        List<NodeDto> nodes = new ArrayList<>(fileSystemNodeDAO.load());
        assertTrue(nodeDto.toString().equals(nodes.get(0).toString())
                && nodeDto2.toString().equals(nodes.get(1).toString()) ||
                nodeDto2.toString().equals(nodes.get(0).toString())
                        && nodeDto.toString().equals(nodes.get(1).toString()) );
    }

    private String getString(final NodeDto node) {
        StringBuilder sb = new StringBuilder();
        node.getRoles().forEach(role -> sb.append(role).append(SeparatorHolder.getSeparatorString()));
        String rolesString = sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();

        return node.getName() + SeparatorHolder.getSeparatorString() + rolesString + System.lineSeparator();
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

        doAnswer(invocation -> null).when(connector).write(anyString(), anyString());

        fileSystemNodeDAO.save(nodeDtos);
    }
}
