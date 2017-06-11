package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exception.VolumeException;

public class VolumeTest {
    
    @Test
    public void testCreateVolume() {
        Volume volume = new Volume("var/x:zzz/x/y");
        assertEquals("var/x", volume.getLocal());
        assertEquals("zzz/x/y", volume.getRemote());
    }

    @Test(expected = VolumeException.class)
    public void testCreateVolume_NullRemote() {
        new Volume("var/x");
    }
    
    @Test(expected = VolumeException.class)
    public void testCreateVolume_NullRemoteWithColon() {
        new Volume("var/x:");
    }
        
    @Test(expected = VolumeException.class)
    public void testCreateVolume_NotVolume() {
        new Volume("var:ngfhgfh:var/x");
    }
}
