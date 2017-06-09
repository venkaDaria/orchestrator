package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
		
		Node expected = container.getNode();
		Assert.assertEquals(expected, node);
		
		List<Container> containers = new ArrayList<>();
		containers.add(container);
		Assert.assertEquals(containers, node.getContainers());
	}
	
	@Test
	public void removeContainerTest() {
		Container container = new Container();
		node.addContainer(container);
		node.removeContainer(container);
		
		Node actual = container.getNode();
		Assert.assertEquals(null, actual);
		
		List<Container> containers = new ArrayList<>();
		Assert.assertEquals(containers, node.getContainers());
	}
}