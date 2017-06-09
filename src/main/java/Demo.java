import java.util.ArrayList;
import java.util.List;

import model.Container;
import exception.ContainerException;
import model.ImageReference;
import model.Node;
import model.Role;
import model.Service;

public class Demo {
	public static void main(String[] args) {
		ImageReference ref = null;
		String[] arr = new String[] { "docker-registry.cloud.sophos/haproxy:dev",
				"docker-registry.cloud.sophos/haproxy@sha256:123abc",
				"docker-registry.cloud.sophos/haproxy:dev@sha256:123abc", };
		for (String el : arr) {
			ref = new ImageReference(el);
			System.out.println(ref);
		}

		System.out.println("----");
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setValue("1");
		roles.add(role);
		role = new Role();
		role.setValue("3");
		roles.add(role);
		Service s = new Service();
		s.setName("name");
		s.setImage(ref);
		s.setRoles(roles);
		System.out.println(s);
		roles.clear();
		role = new Role();
		role.setValue("2");
		roles.add(role);
		role = new Role();
		role.setValue("3");
		roles.add(role);
		Node n = new Node();
		n.setRoles(roles);
		System.out.println(n);

		System.out.println("----");
		n.clearContainers();
		Node n2 = new Node();
		n2.setRoles(roles);
		Container c2;
		try {
			c2 = new Container();
			c2.setNode(n);
			c2.setService(s);
			//n.addContainer(c2);
			n.printContainers();
			n2.addContainer(c2);
		} catch (ContainerException e) {
			System.out.println(e.getMessage());
		}	
	}
}
