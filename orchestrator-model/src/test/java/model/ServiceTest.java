package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ServiceTest {
	
    @Test
    public void testAddContainer() {
    	Service service = new Service();
    	Container container = new Container();
        service.addContainer(container);

        assertEquals(service, container.getService());

        assertFalse(service.getContainers().isEmpty());
        assertTrue(service.getContainers().contains(container));
    }
    
    @Test
    public void testAddContainers() {
    	Service service = new Service();
        
        Container container = new Container();
        Container container2 = new Container();
        Container[] containers = new Container[] { container, container2 };

        service.addContainers(containers);

        assertEquals(service, container.getService());
        assertEquals(service, container2.getService());
        
        assertFalse(service.getContainers().isEmpty());
        assertTrue(service.getContainers().contains(container));
        assertTrue(service.getContainers().contains(container2));
    }

    @Test
    public void testRemoveContainer() {
    	Service service = new Service();
    	Container container = new Container();
        service.addContainer(container);
        service.removeContainer(container);

        assertNull(container.getNode());

        assertTrue(service.getContainers().isEmpty());
    }
    
    @Test
    public void testRemoveContainers() {
    	Service service = new Service();
        
        Container container = new Container();
        Container container2 = new Container();
        Container[] containers = new Container[] { container, container2 };

        service.addContainers(containers);
        service.removeContainers(containers);
        
        assertNull(container.getService());
        assertNull(container2.getService());
        
        assertTrue(service.getContainers().isEmpty());
    }
}
