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
		roles.add(new Role("1"));
		roles.add(new Role("3"));
		Service s = new Service("name", ref, new ArrayList<>(), new ArrayList<>(), roles);
		roles.clear();
		roles.add(new Role("2"));
		roles.add(new Role("3"));
		Node n = new Node(roles);

		System.out.println("----");
		n.clearContainers();
		Node n2 = new Node(roles);
		Container c2;
		try {
			c2 = new Container(n, s);
			//n.addContainer(c2);
			n.printContainers();
			n2.addContainer(c2);
		} catch (ContainerException e) {
			System.out.println(e.getMessage());
		}	
	}
}
