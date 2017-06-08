import java.util.ArrayList;
import java.util.List;

import model.Container;
import exception.ContainerException;
import model.ImageReference;
import model.Node;
import model.Role;
import model.Service;
import exception.ServiceException;
import model.Volume;

public class Demo {
	public static void main(String[] args) throws ContainerException, ServiceException {
		   ImageReference ref = null;
		   String[] arr = new String[] {
			   "docker-registry.cloud.sophos/haproxy:dev",
			   "docker-registry.cloud.sophos/haproxy@sha256:123abc",
			   "docker-registry.cloud.sophos/haproxy:dev@sha256:123abc",
		   };
		   for (String el : arr) {
			   ref = new ImageReference(el);
			   System.out.println(ref);
		   }

		   List<Role> roles = new ArrayList<Role>();
		   roles.add(new Role("1"));
		   roles.add(new Role("3"));
		   Service s = new Service("name", ref, new Volume("x/y/z:var/xxx"), new ArrayList<>(), roles);
		   roles.clear();
		   roles.add(new Role("2"));
		   roles.add(new Role("3"));
		   Node n = new Node(roles);
		   Container c = new Container(n);
		   c.start(s);	
		   System.out.println(s.getRoles());
		   
		   n.clearContainers();
		   Node n2 = new Node(roles);
		   Container c2 = new Container(n);
		   n.addContainer(c2);
		   n.printContainers();
		   n2.addContainer(c2);
	   }
}
