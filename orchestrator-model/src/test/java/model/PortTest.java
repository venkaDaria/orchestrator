package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PortTest {

    @Test
    public void testCreatePort() {
        String line = "tcp:8080:8000";
        Port port = new Port(line);
        assertEquals("tcp", port.getProtocol().getValue());
        assertEquals("8080", port.getLocal().toString());
        assertEquals("8000", port.getRemote().toString());
    }
    
    @Test
    public void testCreatePort_NullRemote() {
        String line = "tcp:8080";
        Port port = new Port(line);
        assertEquals("tcp", port.getProtocol().getValue());
        assertEquals("8080", port.getLocal().toString());
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
