package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

		Node actual = container.getNode();
		assertEquals(node, actual);

		assertFalse(node.getContainers().isEmpty());
	}

	@Test
	public void setServiceTest() {
		Service service = new Service();
		container.setService(service);

		Service actual = container.getService();
		assertEquals(service, actual);

		assertFalse(service.getContainers().isEmpty());
	}
}