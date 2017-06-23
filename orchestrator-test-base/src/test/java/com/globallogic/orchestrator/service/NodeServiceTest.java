package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.database.DatabaseNodeDAO;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.translators.NodeDtoTranslator;
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

public class NodeServiceTest {

    @InjectMocks
    private NodeServiceImpl nodeService;

    @Mock
    private DatabaseNodeDAO nodeDao;

    @Mock
    private NodeDtoTranslator translator;

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

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);

        Node node = new Node();
        node.setName("1");
        node.setRoles(roles);

        when(nodeDao.getByName("1")).thenReturn(nodeDto);
        when(translator.fromDto(nodeDto)).thenReturn(node);

        assertEquals(node, nodeService.getByName("1"));
    }

    @Test
    public void testSave() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        HashSet<Node> nodes = new HashSet<>();

        Node node = new Node();
        node.setName("1");
        node.setRoles(roles);
        nodes.add(node);

        Node node2 = new Node();
        node2.setName("2");
        nodes.add(node2);

        HashSet<NodeDto> nodeDtos = new HashSet<>();

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);
        nodeDtos.add(nodeDto);

        NodeDto nodeDto2 = new NodeDto();
        nodeDto2.setName("2");
        nodeDtos.add(nodeDto2);

        doAnswer(invocation -> null).when(nodeDao).save(nodeDtos);
        when(translator.getDto(node)).thenReturn(nodeDto);
        when(translator.getDto(node2)).thenReturn(nodeDto2);

        nodeService.save(nodes);
    }

    @Test
    public void testLoad() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        HashSet<NodeDto> nodeDtos = new HashSet<>();

        NodeDto nodeDto = new NodeDto();
        nodeDto.setName("1");
        nodeDto.setRoles(rolesString);
        nodeDtos.add(nodeDto);

        NodeDto nodeDto2 = new NodeDto();
        nodeDto2.setName("2");
        nodeDtos.add(nodeDto2);

        HashSet<Node> nodes = new HashSet<>();

        Node node = new Node();
        node.setName("1");
        node.setRoles(roles);
        nodes.add(node);

        Node node2 = new Node();
        node2.setName("2");
        nodes.add(node2);

        when(nodeDao.load()).thenReturn(nodeDtos);
        when(translator.fromDto(nodeDto)).thenReturn(node);
        when(translator.fromDto(nodeDto2)).thenReturn(node2);

        assertEquals(nodes, nodeService.load());
    }
}
