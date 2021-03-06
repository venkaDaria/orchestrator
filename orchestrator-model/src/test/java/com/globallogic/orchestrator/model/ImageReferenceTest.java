package com.globallogic.orchestrator.model;

import com.globallogic.orchestrator.exception.ImageReferenceValidationException;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageReferenceTest {

    @Test
    public void testImageReference() {
        ImageReference image = new ImageReference("docker-registry.cloud.sophos/haproxy:dev@sha256:123abc");

        assertEquals("docker-registry.cloud.sophos", image.getServer());
        assertEquals("haproxy", image.getName());
        assertEquals("dev", image.getTag());
        assertEquals("sha256:123abc", image.getDigestTag());
    }

    @Test
    public void testImageReference_TagNull() {
        ImageReference image = new ImageReference("docker-registry.cloud.sophos/haproxy@sha256:123abc");

        assertEquals("docker-registry.cloud.sophos", image.getServer());
        assertEquals("haproxy", image.getName());
        assertEquals("sha256:123abc", image.getDigestTag());
        assertNull(image.getTag());
    }

    @Test
    public void testImageReference_DigestTagNull() {
        ImageReference image = new ImageReference("docker-registry.cloud.sophos/haproxy:dev");

        assertEquals("docker-registry.cloud.sophos", image.getServer());
        assertEquals("haproxy", image.getName());
        assertEquals("dev", image.getTag());
        assertNull(image.getDigestTag());
    }

    @Test(expected = ImageReferenceValidationException.class)
    public void testImageReference_BothTagNull() {
        new ImageReference("docker-registry.cloud.sophos/haproxy");
    }

    @Test(expected = ImageReferenceValidationException.class)
    public void testImageReference_NameNull() {
        new ImageReference("haproxy:dev@sha256:123ab");
    }

    @Test
    public void testEquals() {
        ImageReference image = new ImageReference("docker-registry.cloud.sophos/haproxy@sha256:123abc");
        ImageReference image2 = new ImageReference("docker-registry.cloud.sophos/haproxy@sha256:123abc");
        assertEquals(image, image2);
    }

    @Test
    public void testEquals_False() {
        ImageReference image = new ImageReference("docker-registry.cloud.sophos/haproxy:123abc");
        ImageReference image2 = new ImageReference("docker-registry.cloud.sophos/haproxy@sha256:123abc");
        assertNotEquals(image, image2);
    }
}
