package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
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

public class FileSystemServiceDAOTest {

    @InjectMocks
    private FileSystemServiceDAOImpl fileSystemServiceDAO;

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

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);
        serviceDto.setPorts(new HashSet<>());
        serviceDto.setVolumes(new HashSet<>());

        when(connector.read(anyString())).thenReturn(getString(serviceDto));

        assertEquals(serviceDto.toString(), fileSystemServiceDAO.getByName("1").toString());
    }

    @Test
    public void testLoad() {
        Set<ServiceDto> serviceDtos = new LinkedHashSet<>();

        Set<String> rolesString = new HashSet<>();
        rolesString.add("1");
        rolesString.add("2");

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setName("1");
        serviceDto.setRoles(rolesString);
        serviceDto.setPorts(new HashSet<>());
        serviceDto.setVolumes(new HashSet<>());
        serviceDtos.add(serviceDto);

        ServiceDto serviceDto2 = new ServiceDto();
        serviceDto2.setName("2");
        serviceDto2.setRoles(rolesString);
        serviceDto2.setPorts(new HashSet<>());
        serviceDto2.setVolumes(new HashSet<>());
        serviceDtos.add(serviceDto2);

        when(connector.read(anyString())).thenReturn(getString(serviceDto) + getString(serviceDto2));

        List<ServiceDto> services = new ArrayList<>(fileSystemServiceDAO.load());
        assertTrue(serviceDto.toString().equals(services.get(0).toString())
                && serviceDto2.toString().equals(services.get(1).toString()) ||
                serviceDto2.toString().equals(services.get(0).toString())
                        && serviceDto.toString().equals(services.get(1).toString()) );
    }

    private String getString(final ServiceDto service) {
        return service.getName() + SeparatorHolder.getSeparatorString() + service.getImage() +
                SeparatorHolder.getSeparatorString() + getString(service.getPorts()) + SeparatorHolder.getSeparatorString() +
                SeparatorHolder.getSeparatorString() + getString(service.getRoles()) + SeparatorHolder.getSeparatorString() +
                SeparatorHolder.getSeparatorString() + getString(service.getVolumes()) +
                System.lineSeparator();
    }

    private String getString(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        set.forEach(el -> sb.append(el).append(SeparatorHolder.getSeparatorString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
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
        serviceDto.setPorts(new HashSet<>());
        serviceDto.setVolumes(new HashSet<>());
        serviceDtos.add(serviceDto);

        ServiceDto serviceDto2 = new ServiceDto();
        serviceDto2.setName("2");
        serviceDto2.setRoles(rolesString);
        serviceDto2.setPorts(new HashSet<>());
        serviceDto2.setVolumes(new HashSet<>());
        serviceDtos.add(serviceDto2);

        doAnswer(invocation -> null).when(connector).write(anyString(), anyString());

        fileSystemServiceDAO.save(serviceDtos);
    }
}
