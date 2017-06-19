package com.globallogic.orchestrator.dao.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import com.globallogic.orchestrator.dao.model.entity.Container;
import com.globallogic.orchestrator.dao.model.entity.Service;
import com.globallogic.orchestrator.dao.model.valueobject.ImageReference;
import com.globallogic.orchestrator.dao.model.valueobject.Role;
import org.junit.Test;

public class ServiceTest {
	
    @Test
    public void testAddContainer() {
    	Service service = new Service();
    	Container container = new Container();
        service.addContainer(container);

        assertEquals(service, container.getService());

        assertFalse(service.getContainers().isEmpty());
        assertTrue(service.getContainers().contains(container));
    }
    
    @Test
    public void testAddContainers() {
    	Service service = new Service();
        
        Container container = new Container();
        Container container2 = new Container();
        Container[] containers = new Container[] { container, container2 };

        service.addContainers(containers);

        assertEquals(service, container.getService());
        assertEquals(service, container2.getService());
        
        assertFalse(service.getContainers().isEmpty());
        assertTrue(service.getContainers().contains(container));
        assertTrue(service.getContainers().contains(container2));
    }

    @Test
    public void testRemoveContainer() {
    	Service service = new Service();
    	Container container = new Container();
        service.addContainer(container);
        service.removeContainer(container);

        assertNull(container.getNode());

        assertTrue(service.getContainers().isEmpty());
    }
    
    @Test
    public void testRemoveContainers() {
    	Service service = new Service();
        
        Container container = new Container();
        container.setId("i");
        Container container2 = new Container();
        container2.setId("j");
        Container[] containers = new Container[] { container, container2 };

        service.addContainers(containers);
        service.removeContainers(containers);
        
        assertNull(container.getService());
        assertNull(container2.getService());
        
        assertTrue(service.getContainers().isEmpty());
    }
    
    @Test
    public void testEquals() {
    	Set<Role> roles = new HashSet<Role>();
		Role role = new Role("1");
		roles.add(role);
		role = new Role("3");
		roles.add(role);
		
		Service s = new Service();
		s.setName("name");
		s.setImage(new ImageReference("docker-registry.cloud.sophos/haproxy:dev"));
		s.setRoles(roles);
		s.setPorts(new HashSet<>());
		s.setVolumes(new HashSet<>());
		
    	Service s2 = s.copy();
    	
        assertEquals(s, s2);
    }

    @Test
    public void testEquals_False() {
    	Set<Role> roles = new HashSet<Role>();
		Role role = new Role("1");
		roles.add(role);
		role = new Role("3");
		roles.add(role);
		
		Service s = new Service();
		s.setName("name");
		s.setImage(new ImageReference("docker-registry.cloud.sophos/haproxy:dev"));
		s.setRoles(roles);
		s.setPorts(new HashSet<>());
		s.setVolumes(new HashSet<>());
		
    	Service s2 = s.copy();
    	s2.setName("jhj");
    	
        assertNotEquals(s, s2);
    }
}
