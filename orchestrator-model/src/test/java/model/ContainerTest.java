package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ContainerTest {
	Container container = new Container();
	
	@Before
	public void initialize() {
		container = new Container();
	}
	
	@Test
	public void setNodeTest() {
		Node node = new Node();
		container.setNode(node);
		
		assertEquals(node, container.getNode());

		assertFalse(node.getContainers().isEmpty());
		assertTrue(node.getContainers().contains(container));
	}

	@Test
	public void setServiceTest() {
		Service service = new Service();
		container.setService(service);

		assertEquals(service, container.getService());

		assertFalse(service.getContainers().isEmpty());
		assertTrue(service.getContainers().contains(container));
	}
	
	@Test
	public void setNodeNullTest() {
		Node node = new Node();
		container.setNode(node);		
		container.setNode(null);
		
		assertNull(container.getNode());
	    assertTrue(node.getContainers().isEmpty());
	}

	@Test
	public void setServiceNullTest() {
		Service service = new Service();
		container.setService(service);	
		container.setService(null);
		
		assertNull(container.getService());
	    assertTrue(service.getContainers().isEmpty());
	}
}