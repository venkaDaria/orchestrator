package com.globallogic.orchestrator.dao.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import com.globallogic.orchestrator.dao.exception.PortValidationException;
import com.globallogic.orchestrator.dao.model.valueobject.Port;
import org.junit.Test;

public class PortTest {

	@Test
	public void testCreatePort() {
		Port port = new Port("8080:8000/tcp");

		assertEquals("tcp", port.getProtocol().getValue());
		assertEquals("8080", port.getLocal().toString());
		assertEquals("8000", port.getRemote().toString());
	}

	@Test
	public void testCreatePort_NullRemote() {
		Port port = new Port("8080/http");

		assertEquals("http", port.getProtocol().getValue());
		assertEquals("8080", port.getLocal().toString());
		assertNull(port.getRemote());
	}

	@Test
	public void testCreatePort_NullProtocol() {
		Port port = new Port("8080");

		assertEquals("tcp", port.getProtocol().getValue());
		assertEquals("8080", port.getLocal().toString());
		assertNull(port.getRemote());
	}

	@Test(expected = PortValidationException.class)
	public void testCreatePort_SpaceLocal() {
		new Port("     :9090/http");
	}

	@Test(expected = PortValidationException.class)
	public void testCreatePort_SpaceProtocolAndLocal() {
		new Port("   :9080:   ");
	}

	@Test(expected = NumberFormatException.class)
	public void testCreatePort_NullLocal() {
		new Port("tcp");
	}

	@Test(expected = NumberFormatException.class)
	public void testCreatePort_NotNumber() {
		new Port("jhgj:hgg/tcp");
	}

	@Test
	public void testEquals() {
		Port port = new Port("8080:8080/http");
		Port port2 = new Port("8080:8080/http");
		assertEquals(port, port2);
	}

	@Test
	public void testEquals_False() {
		Port port = new Port("8080:8080/http");
		Port port2 = new Port("8080/http");
		assertNotEquals(port, port2);
	}
}
