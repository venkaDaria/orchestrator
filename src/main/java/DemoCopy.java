import java.util.HashSet;
import java.util.Set;

import model.entity.Container;
import model.entity.Node;
import model.entity.Service;
import model.valueobject.ImageReference;
import model.valueobject.Role;

public class DemoCopy {

	public static void main(String[] args) {
		Service s = new Service();
		s.setName("one");
		ImageReference ref = new ImageReference("docker-registry.cloud.sophos/haproxy:dev");
		s.setImage(ref);
		s.setPorts(new HashSet<>());
		s.setVolumes(new HashSet<>());
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role("1");
		roles.add(role);
		s.setRoles(roles);

		Service s2 = s.copy();
		s2.setName("two");

		Node n = new Node();
		n.setName("one");
		n.setRoles(roles);

		Node n2 = n.copy();
		n2.setName("two");

		Container c = new Container();
		c.setId("1");
		Container c2 = new Container();
		c2.setId("2");
		Container c3 = new Container();
		c3.setId("3");
		Container c4 = new Container();
		c4.setId("4");

		c.setNode(n);
		c.setService(s2);
		// c.setService(s);

		c2.setNode(n);
		c2.setService(s);
		// c3.setService(s2);

		c3.setNode(n2);
		c3.setService(s);

		c4.setNode(n2);
		c4.setService(s2);

		n.getContainers().forEach(x -> System.out.println("N " + x));
		n2.getContainers().forEach(x -> System.out.println("N2 " + x));
		s.getContainers().forEach(x -> System.out.println("S " + x));
		s2.getContainers().forEach(x -> System.out.println("S2 " + x));

		Node n3 = n2.copyWithContainers();
		Service s3 = s.copyWithContainers();

		s3.getContainers().forEach(x -> x.getNode().setName(x.getNode().getName() + " s3"));
		n3.getContainers().forEach(x -> x.getService().setName(x.getService().getName() + " n3"));

		n.getContainers().forEach(x -> System.out.println("N " + x));
		n2.getContainers().forEach(x -> System.out.println("N2 " + x));
		n3.getContainers().forEach(x -> System.out.println("N3 " + x));
		s.getContainers().forEach(x -> System.out.println("S " + x));
		s2.getContainers().forEach(x -> System.out.println("S2 " + x));
		s3.getContainers().forEach(x -> System.out.println("S3 " + x));
	}
}
