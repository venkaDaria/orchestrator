package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import exception.StringValidationException;
import model.valueobject.Role;

public class RoleTest {

	@Test
	public void testCreateRole() {
		Role role = new Role("data");

		assertEquals("data", role.getValue());
	}

	@Test(expected = StringValidationException.class)
	public void testCreateRole_Empty() {
		new Role("   ");
	}

	@Test(expected = StringValidationException.class)
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
