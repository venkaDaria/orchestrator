package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import exception.PortException;

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
    
    @Test(expected = PortException.class)
    public void testCreatePort_SpaceLocal() {
        new Port("     :9090/http");
    }
    
    @Test(expected = PortException.class)
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
}
