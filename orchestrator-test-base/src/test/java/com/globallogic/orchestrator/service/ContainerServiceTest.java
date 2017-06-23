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

import static org.junit.Assert.assertEquals;
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
    public void tesGetById() {
        ContainerDto contDto = new ContainerDto();
        contDto.setId("1");
        contDto.setNodeName("node");
        contDto.setServiceName("service");

        Container cont = new Container();
        cont.setId("1");

        when(containerDao.getById("1")).thenReturn(contDto);
        when(translator.fromDto(contDto)).thenReturn(cont);

        Container cont2 = containerService.getById("1");
        assertEquals(cont, cont2);
    }
}
