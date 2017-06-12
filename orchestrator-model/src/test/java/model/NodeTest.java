package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testAddContainer() {
		Node node = new Node();
		Container container = new Container();
		node.addContainer(container);

		assertEquals(node, container.getNode());

		assertFalse(node.getContainers().isEmpty());
		assertTrue(node.getContainers().contains(container));
	}

	@Test
	public void testAddContainers() {
		Node node = new Node();

		Container container = new Container();
		Container container2 = new Container();
		Container[] containers = new Container[] { container, container2 };

		node.addContainers(containers);

		assertEquals(node, container.getNode());
		assertEquals(node, container2.getNode());

		assertFalse(node.getContainers().isEmpty());
		assertTrue(node.getContainers().contains(container));
		assertTrue(node.getContainers().contains(container2));
	}

	@Test
	public void testRemoveContainer() {
		Node node = new Node();
		Container container = new Container();

		node.addContainer(container);
		node.removeContainer(container);

		assertNull(container.getNode());

		assertTrue(node.getContainers().isEmpty());
	}

	@Test
	public void testRemoveContainers() {
		Node node = new Node();

		Container container = new Container();
		Container container2 = new Container();
		Container[] containers = new Container[] { container, container2 };

		node.addContainers(containers);
		node.removeContainers(containers);

		assertNull(container.getNode());
		assertNull(container2.getNode());

		assertTrue(node.getContainers().isEmpty());
	}
}