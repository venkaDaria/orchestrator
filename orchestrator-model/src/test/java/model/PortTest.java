package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PortTest {

	@Test
	public void testCreatePort() {
		String line = "tcp:8080:8000";
		Port port = new Port(line);
		assertEquals(line, port.toString());
	}
	
	@Test
	public void testCreatePort_NullRemote() {
		String line = "tcp:8080";
		Port port = new Port(line);
		assertEquals(line, port.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreatePort_NullProtocol() {
		new Port(":8080");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreatePort_NullLocal() {
		new Port("tcp");
	}
	
	@Test(expected = NumberFormatException.class)
	public void testCreatePort_NotNumber() {
		new Port("tcp:jhgj:hgg");
	}
}
