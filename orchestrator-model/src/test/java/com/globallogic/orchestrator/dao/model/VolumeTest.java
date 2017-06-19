package com.globallogic.orchestrator.dao.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.globallogic.orchestrator.dao.exception.VolumeValidationException;
import com.globallogic.orchestrator.dao.model.valueobject.Volume;
import org.junit.Test;

public class VolumeTest {

	@Test
	public void testCreateVolume() {
		Volume volume = new Volume("var/x:zzz/x/y");
		assertEquals("var/x", volume.getLocal());
		assertEquals("zzz/x/y", volume.getRemote());
	}

	@Test(expected = VolumeValidationException.class)
	public void testCreateVolume_NullRemote() {
		new Volume("var/x");
	}

	@Test(expected = VolumeValidationException.class)
	public void testCreateVolume_NullRemoteWithColon() {
		new Volume("var/x:");
	}

	@Test(expected = VolumeValidationException.class)
	public void testCreateVolume_NotVolume() {
		new Volume("var:ngfhgfh:var/x");
	}

	@Test
	public void testEquals() {
		Volume volume = new Volume("var/x:zzz/x/y");
		Volume volume2 = new Volume("var/x:zzz/x/y");
		assertEquals(volume, volume2);
	}

	@Test
	public void testEquals_False() {
		Volume volume = new Volume("var/x:zzz/x/y");
		Volume volume2 = new Volume("var/x:k");
		assertNotEquals(volume, volume2);
	}
}
