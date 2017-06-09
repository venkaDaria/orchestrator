package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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

		Service expected = container.getService();
		Assert.assertEquals(expected, service);

		List<Container> containers = new ArrayList<>();
		containers.add(container);
		Assert.assertEquals(containers, service.getContainers());
	}

	@Test
	public void removeContainerTest() {
		Container container = new Container();
		service.addContainer(container);
		service.removeContainer(container);

		Node actual = container.getNode();
		Assert.assertEquals(null, actual);

		List<Container> containers = new ArrayList<>();
		Assert.assertEquals(containers, service.getContainers());
	}
}
