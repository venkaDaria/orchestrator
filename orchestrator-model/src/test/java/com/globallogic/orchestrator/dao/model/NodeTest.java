package com.globallogic.orchestrator.dao.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.globallogic.orchestrator.dao.model.entity.Container;
import com.globallogic.orchestrator.dao.model.entity.Node;
import com.globallogic.orchestrator.dao.model.valueobject.Role;

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
		node.setName("name");

		Container container = new Container();
		container.setId("1");

		node.addContainer(container);
		node.removeContainer(container);

		assertNull(container.getNode());

		assertTrue(node.getContainers().isEmpty());
	}

	@Test
	public void testRemoveContainers() {
		Node node = new Node();
		node.setName("name");

		Container container = new Container();
		container.setId("i");
		Container container2 = new Container();
		container2.setId("j");
		Container[] containers = new Container[] { container, container2 };

		node.addContainers(containers);
		node.removeContainers(containers);

		assertNull(container.getNode());
		assertNull(container2.getNode());

		assertTrue(node.getContainers().isEmpty());
	}

	@Test
	public void testEquals() {
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role("1");
		roles.add(role);
		role = new Role("3");
		roles.add(role);

		Node node = new Node();
		node.setName("hgh");
		node.setRoles(roles);

		Node node2 = node.copy();

		assertEquals(node, node2);
	}

	@Test
	public void testEquals_False() {
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role("1");
		roles.add(role);
		role = new Role("3");
		roles.add(role);

		Node node = new Node();
		node.setName("hgh");
		node.setRoles(roles);

		Node node2 = node.copy();
		node2.setName("hh");

		assertNotEquals(node, node2);
	}
}