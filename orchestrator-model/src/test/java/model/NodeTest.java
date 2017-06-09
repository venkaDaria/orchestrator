package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {

    Node node;
    
    @Before
    public void initialize() {
       node = new Node();
    }	
	
	@Test
	public void addContainerTest() {
		Container container = new Container();
		node.addContainer(container);
		
		Node actual = container.getNode();
		assertEquals(actual, node);
		
		assertFalse(node.getContainers().isEmpty());
	}
	
	@Test
	public void removeContainerTest() {
		Container container = new Container();
		node.addContainer(container);
		node.removeContainer(container);
		
		Node actual = container.getNode();
		assertNull(actual);
		
		assertTrue(node.getContainers().isEmpty());
	}
}