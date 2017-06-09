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
	public void testAddContainer() {
		Container container = new Container();
		service.addContainer(container);

		assertEquals(service, container.getService());

		assertFalse(service.getContainers().isEmpty());
		assertTrue(service.getContainers().contains(container));
	}

	@Test
	public void testRemoveContainer() {
		Container container = new Container();
		service.addContainer(container);
		service.removeContainer(container);

		assertNull(container.getNode());

		assertTrue(service.getContainers().isEmpty());
	}
}
