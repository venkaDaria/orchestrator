import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.PortException;
import model.Container;
import model.ImageReference;
import model.Node;
import model.Role;
import model.Service;

public class Demo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Pattern p = Pattern.compile("^(.+?)(:(.+?))?(\\/(.+)?)?$");  
	    Matcher m = p.matcher("8080:8000/tcp");  
	    m.matches();
	    System.out.println(m.group());	

		ImageReference ref = null;
		String[] arr = new String[] { "docker-registry.cloud.sophos/haproxy:dev",
				"docker-registry.cloud.sophos/haproxy@sha256:123abc",
				"docker-registry.cloud.sophos/haproxy:dev@sha256:123abc", };
		for (String el : arr) {
			ref = new ImageReference(el);
			System.out.println(ref);
		}
		
		System.out.println("----");
		System.out.println(new PortException().getCode());
		System.out.println("----");
		
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role("1");
		roles.add(role);
		role = new Role("3");
		roles.add(role);
		Service s = new Service();
		s.setName("name");
		s.setImage(ref);
		s.setRoles(roles);
		System.out.println(s);
		roles.clear();
		role = new Role("2");
		roles.add(role);
		role = new Role("3");
		roles.add(role);
		Node n = new Node();
		n.setName("hghg");
		n.setRoles(roles);
		System.out.println(n);

		System.out.println("----");
		Node n2 = new Node();
		n2.setName("hjhj");
		n2.setRoles(roles);
		Container c2 = new Container();
		c2.setNode(n);
		c2.setService(s);
		n2.addContainer(c2);
		System.out.println(c2);
		System.out.println(n2);
		System.out.println(s);
		System.out.println("----");
	}
}

// TODO: copy bi-link
// TODO: check add-remove-clear with hashcode-equals