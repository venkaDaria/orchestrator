package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exception.ProtocolException;

public class ProtocolTest {

    @Test
    public void testCreateProtocol() {
    	Protocol protocol = new Protocol("http");
    	
        assertEquals("http", protocol.getValue());
    }
    
    @Test(expected = ProtocolException.class)
    public void testCreateProtocol_Empty() {
    	new Protocol("   ");
    }
    
    @Test(expected = ProtocolException.class)
    public void testCreateProtocol_Null() {
    	new Protocol(null);
    }
}
