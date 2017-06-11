package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ContainerTest {   
    @Test
    public void testSetNode() {
    	Container container = new Container();
        Node node = new Node();
        container.setNode(node);
        
        assertEquals(node, container.getNode());

        assertFalse(node.getContainers().isEmpty());
        assertTrue(node.getContainers().contains(container));
    }

    @Test
    public void testSetService() {
    	Container container = new Container();
        Service service = new Service();
        container.setService(service);

        assertEquals(service, container.getService());

        assertFalse(service.getContainers().isEmpty());
        assertTrue(service.getContainers().contains(container));
    }
    
    @Test
    public void testSetNode_NullValue() {
    	Container container = new Container();
        Node node = new Node();
        container.setNode(node);        
        container.setNode(null);
        
        assertNull(container.getNode());
        assertTrue(node.getContainers().isEmpty());
    }

    @Test
    public void testSetService_NullValue() {
    	Container container = new Container();
        Service service = new Service();
        container.setService(service);    
        container.setService(null);
        
        assertNull(container.getService());
        assertTrue(service.getContainers().isEmpty());
    }
}