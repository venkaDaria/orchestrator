package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
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

public class FileSystemContainerDAOTest {

    @InjectMocks
    private FileSystemContainerDAOImpl fileSystemContainerDAO;

    @Mock
    private FileSystemConnector connector;

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

        when(connector.read(anyString())).thenReturn(getString(contDto));

        assertEquals(contDto.toString(), fileSystemContainerDAO.getById("1").toString());
    }

    @Test
    public void testLoad() {
        Set<ContainerDto> containerDtos = new LinkedHashSet<>();

        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");
        contDto.setStatus("NONE");
        containerDtos.add(contDto);

        ContainerDto contDto2 = new ContainerDto();
        contDto2.setId("2");
        contDto2.setStatus("NONE");
        containerDtos.add(contDto2);

        when(connector.read(anyString())).thenReturn(getString(contDto) + getString(contDto2));

        List<ContainerDto> conts = new ArrayList<>(fileSystemContainerDAO.load());
        assertTrue(contDto.toString().equals(conts.get(0).toString())
                && contDto2.toString().equals(conts.get(1).toString()) ||
                contDto2.toString().equals(conts.get(0).toString())
                        && contDto.toString().equals(conts.get(1).toString()) );
    }

    private String getString(final ContainerDto contDto) {
        return contDto.getId() + SeparatorHolder.getSeparatorString() + contDto.getNodeName()
                + SeparatorHolder.getSeparatorString() + contDto.getServiceName() + SeparatorHolder.getSeparatorString()
                + contDto.getStatus() + System.lineSeparator();
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

        doAnswer(invocation -> null).when(connector).write(anyString(), anyString());

        fileSystemContainerDAO.save(containerDtos);
    }
}
