package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.database.DatabaseContainerDAO;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class ContainerServiceTest {

    @InjectMocks
    private ContainerServiceImpl containerService;

    @Mock
    private DatabaseContainerDAO containerDao;

    @Mock
    private ContainerDtoTranslator translator;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");

        Container cont = new Container();
        cont.setId("1");

        when(containerDao.getById("1")).thenReturn(contDto);
        when(translator.fromDto(contDto, null, null)).thenReturn(cont);

        assertEquals(cont, containerService.getById("1"));
    }

    @Test
    public void testSave() {
        HashSet<Container> containers = new HashSet<>();

        Container cont = new Container();
        cont.setId("1");
        containers.add(cont);

        Container cont2 = new Container();
        cont2.setId("2");
        containers.add(cont2);

        HashSet<ContainerDto> containerDtos = new HashSet<>();

        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");
        containerDtos.add(contDto);

        ContainerDto contDto2 = new ContainerDto();
        contDto2.setId("2");
        containerDtos.add(contDto2);

        doAnswer(invocation -> null).when(containerDao).save(containerDtos);
        when(translator.getDto(cont)).thenReturn(contDto);
        when(translator.getDto(cont2)).thenReturn(contDto2);

        containerService.save(containers);
    }

    @Test
    public void testLoad() {
        HashSet<ContainerDto> containerDtos = new HashSet<>();

        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");
        contDto.setStatus("NONE");
        containerDtos.add(contDto);

        ContainerDto contDto2 = new ContainerDto();
        contDto2.setId("2");
        contDto2.setStatus("NONE");
        containerDtos.add(contDto2);

        HashSet<Container> containers = new HashSet<>();

        Container cont = new Container();
        cont.setId("1");
        containers.add(cont);

        Container cont2 = new Container();
        cont2.setId("2");
        containers.add(cont2);

        when(containerDao.load()).thenReturn(containerDtos);
        when(translator.fromDto(contDto, null, null)).thenReturn(cont);
        when(translator.fromDto(contDto2, null, null)).thenReturn(cont2);

        assertEquals(containers, containerService.load());
    }
}
