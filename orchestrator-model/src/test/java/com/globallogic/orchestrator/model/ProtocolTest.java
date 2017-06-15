package com.globallogic.orchestrator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.globallogic.orchestrator.base.StringValidationException;
import com.globallogic.orchestrator.model.valueobject.Protocol;

public class ProtocolTest {

	@Test
	public void testCreateProtocol() {
		Protocol protocol = new Protocol("http");

		assertEquals("http", protocol.getValue());
	}

	@Test(expected = StringValidationException.class)
	public void testCreateProtocol_Empty() {
		new Protocol("   ");
	}

	@Test(expected = StringValidationException.class)
	public void testCreateProtocol_Null() {
		new Protocol(null);
	}

	@Test
	public void testEquals() {
		Protocol protocol = new Protocol("http");
		Protocol protocol2 = new Protocol("http");
		assertEquals(protocol, protocol2);
	}

	@Test
	public void testEquals_False() {
		Protocol protocol = new Protocol("http");
		Protocol protocol2 = new Protocol("htp");
		assertNotEquals(protocol, protocol2);
	}
}
