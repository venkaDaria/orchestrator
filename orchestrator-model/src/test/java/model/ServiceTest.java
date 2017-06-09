package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

	Service service;

	@Before
	public void initialize() {
		service = new Service();
	}

	@Test
	public void addContainerTest() {
		Container container = new Container();
		service.addContainer(container);

		Service actual = container.getService();
		assertEquals(actual, service);

		assertFalse(service.getContainers().isEmpty());
	}

	@Test
	public void removeContainerTest() {
		Container container = new Container();
		service.addContainer(container);
		service.removeContainer(container);

		Node actual = container.getNode();
		assertNull(actual);

		assertTrue(service.getContainers().isEmpty());
	}
}
