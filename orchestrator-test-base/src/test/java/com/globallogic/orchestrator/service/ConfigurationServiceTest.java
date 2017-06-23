package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ConfigurationServiceTest {

    @InjectMocks
    private ConfigurationServiceImpl configurationService;

    @Mock
    private NodeService nodeService;

    @Mock
    private ServiceService serviceService;

    @Mock
    private ContainerService containerService;

    @Mock
    private ContainerDAO containerDAO;

    @Mock
    private ContainerDtoTranslator translator;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoad() {
        Configuration config = new Configuration();

        Service s = new Service();
        s.setName("one");
        ImageReference ref = new ImageReference("docker-registry.cloud.sophos/haproxy:dev");
        s.setImage(ref);
        s.setPorts(new HashSet<>());
        s.setVolumes(new HashSet<>());

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("1"));
        roles.add(new Role("2"));
        s.setRoles(roles);

        Service s2 = s.copy();
        s2.setName("two");

        Node n = new Node();
        n.setName("one");
        n.setRoles(roles);

        Node n2 = n.copy();
        n2.setName("two");

        Container c = new Container();
        c.setId("1");
        Container c2 = new Container();
        c2.setId("2");
        Container c3 = new Container();
        c3.setId("3");
        Container c4 = new Container();
        c4.setId("4");

        c.setNode(n);
        c.setService(s2);

        c2.setNode(n);
        c2.setService(s);

        c3.setNode(n2);
        c3.setService(s);

        c4.setNode(n2);
        c4.setService(s2);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n);
        nodes.add(n2);

        Set<Service> services = new HashSet<>();
        services.add(s);
        services.add(s2);

        config.setNodes(nodes);
        config.setServices(services);

        Set<ContainerDto> containerDtos = new HashSet<>();

        ContainerDto cDto = new ContainerDto();
        cDto.setId("1");
        cDto.setNodeName("one");
        cDto.setServiceName("one");

        ContainerDto cDto2 = new ContainerDto();
        cDto2.setId("2");
        cDto2.setNodeName("one");
        cDto2.setServiceName("two");

        ContainerDto cDto3 = new ContainerDto();
        cDto3.setId("3");
        cDto3.setNodeName("two");
        cDto3.setServiceName("one");

        ContainerDto cDto4 = new ContainerDto();
        cDto4.setId("4");
        cDto4.setNodeName("two");
        cDto4.setServiceName("two");

        containerDtos.add(cDto);
        containerDtos.add(cDto2);
        containerDtos.add(cDto3);
        containerDtos.add(cDto4);

        when(containerDAO.load()).thenReturn(containerDtos);
        when(serviceService.load()).thenReturn(services);
        when(nodeService.load()).thenReturn(nodes);

        when(translator.fromDto(cDto)).thenReturn(c);
        when(translator.fromDto(cDto2)).thenReturn(c2);
        when(translator.fromDto(cDto3)).thenReturn(c3);
        when(translator.fromDto(cDto4)).thenReturn(c4);
        
        Configuration conf = configurationService.load();

        assertEquals(config.getNodes(), conf.getNodes());
        assertEquals(config.getServices(), conf.getServices());
    }

    @Test
    public void testSave() {

    }
}
