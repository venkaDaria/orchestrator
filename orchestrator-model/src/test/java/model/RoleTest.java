package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exception.RoleException;

public class RoleTest {

    @Test
    public void testCreateRole() {
    	Role role = new Role("data");
    	
        assertEquals("data", role.getValue());
    }
    
    @Test(expected = RoleException.class)
    public void testCreateRole_Empty() {
    	new Role("   ");
    }
    
    @Test(expected = RoleException.class)
    public void testCreateRole_Null() {
    	new Role(null);
    }
}
