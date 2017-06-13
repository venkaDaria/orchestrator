package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		service.setPorts(new ArrayList<>());
		service.setVolumes(new ArrayList<>());
		service.setRoles(new ArrayList<>());

		Node node = new Node();
		node.setRoles(new ArrayList<>());

		container.setId("4");
		container.setNode(node);
		container.setService(service);
		container.setStatus(Status.STOPPED);

		Container container2 = container.copy();

		assertEquals(container, container2);
	}

	@Test
	public void testEquals_False() {
		Container container = new Container();

		Service service = new Service();
		service.setPorts(new ArrayList<>());
		service.setVolumes(new ArrayList<>());
		service.setRoles(new ArrayList<>());

		Node node = new Node();
		node.setRoles(new ArrayList<>());

		container.setId("4");
		container.setNode(node);
		container.setService(service);
		container.setStatus(Status.STOPPED);

		Container container2 = container.copy();
		container2.setId("5");

		assertNotEquals(container, container2);
	}

	@Test
	public void testEquals_FalseService() {
		Container container = new Container();

		Service service = new Service();
		service.setPorts(new ArrayList<>());
		service.setVolumes(new ArrayList<>());
		service.setRoles(new ArrayList<>());

		Node node = new Node();
		node.setRoles(new ArrayList<>());

		container.setId("4");
		container.setNode(node);
		container.setService(service);
		container.setStatus(Status.STOPPED);

		Container container2 = container.copy();

		Service service2 = service.copy();
		service2.setName("hg");

		container2.setService(service2);

		assertNotEquals(container, container2);
	}

	@Test
	public void testEquals_FalseNode() {
		Container container = new Container();

		Service service = new Service();
		service.setPorts(new ArrayList<>());
		service.setVolumes(new ArrayList<>());
		service.setRoles(new ArrayList<>());

		Node node = new Node();
		node.setRoles(new ArrayList<>());

		container.setId("4");
		container.setNode(node);
		container.setService(service);
		container.setStatus(Status.STOPPED);

		Container container2 = container.copy();

		Node node2 = new Node();
		node2.setName("hghg");

		container2.setNode(node2);

		assertNotEquals(container, container2);
	}
}
