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
	public void testAddContainer() {
		Container container = new Container();
		node.addContainer(container);

		assertEquals(node, container.getNode());
		
		assertFalse(node.getContainers().isEmpty());
		assertTrue(node.getContainers().contains(container));
	}
	
	@Test
	public void testRemoveContainer() {
		Container container = new Container();
		node.addContainer(container);
		node.removeContainer(container);

		assertNull(container.getNode());
		
		assertTrue(node.getContainers().isEmpty());
	}
}