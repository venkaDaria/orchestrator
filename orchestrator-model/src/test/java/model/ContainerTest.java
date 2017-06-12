package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    
    @Test
    public void testEquals() {
    	Container container = new Container();
    	Service service = new Service();
    	Node node = new Node();
    	
        container.setId(4); 
        container.setNode(node);  
        container.setService(service);  
        container.setStatus(Status.STOPPED);
        
        Container container2 = new Container();
        container2.setId(4);   
        container2.setNode(node);  
        container2.setService(service);  
        container2.setStatus(Status.STOPPED);
        
        assertEquals(container, container2);
    }

    @Test
    public void testEquals_False() {
    	Container container = new Container();
    	Service service = new Service();
    	Node node = new Node();
    	
        container.setId(4); 
        container.setNode(node);  
        container.setService(service);  
        container.setStatus(Status.STOPPED);
        
        Container container2 = new Container();
        container2.setId(5);   
        container2.setNode(node);  
        container2.setService(service);  
        container2.setStatus(Status.STOPPED);
        
        assertNotEquals(container, container2);
    }   
}
