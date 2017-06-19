package com.globallogic.orchestrator.dao.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.globallogic.orchestrator.dao.model.valueobject.Role;
import org.junit.Test;

import com.globallogic.orchestrator.dao.exception.StringValueObjectValidationException;

public class RoleTest {

	@Test
	public void testCreateRole() {
		Role role = new Role("data");

		assertEquals("data", role.getValue());
	}

	@Test(expected = StringValueObjectValidationException.class)
	public void testCreateRole_Empty() {
		new Role("   ");
	}

	@Test(expected = StringValueObjectValidationException.class)
	public void testCreateRole_Null() {
		new Role(null);
	}

	@Test
	public void testEquals() {
		Role role = new Role("data");
		Role role2 = new Role("data");
		assertEquals(role, role2);
	}

	@Test
	public void testEquals_False() {
		Role role = new Role("data");
		Role role2 = new Role("dat");
		assertNotEquals(role, role2);
	}
}
