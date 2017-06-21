import com.globallogic.orchestrator.dao.DAOSystem;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.filesystem.LocaleSeparator;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.ImageReference;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.ConfigurationServiceImpl;
import com.globallogic.orchestrator.service.interfaces.ConfigurationService;

import java.util.HashSet;
import java.util.Set;

public class Demo {

    public static void main(String[] args) {
        Configuration config = new Configuration();

        Service s = new Service();
        s.setName("one");
        ImageReference ref = new ImageReference("docker-registry.cloud.sophos/haproxy:dev");
        s.setImage(ref);
        s.setPorts(new HashSet<>());
        s.setVolumes(new HashSet<>());
        Set<Role> roles = new HashSet<>();
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

        Set<Node> nodes = new HashSet<>();
        nodes.add(n);
        nodes.add(n2);

        Set<Service> services = new HashSet<>();
        services.add(s);
        services.add(s2);

        n.getContainers().forEach(x -> System.out.println("N " + x));
        n2.getContainers().forEach(x -> System.out.println("N2 " + x));
        s.getContainers().forEach(x -> System.out.println("S " + x));
        s2.getContainers().forEach(x -> System.out.println("S2 " + x));
        System.out.println();

        config.setNodes(nodes);
        config.setServices(services);

        SeparatorHolder.setSeparator(LocaleSeparator.COMMA);
        ConfigurationService cs = new ConfigurationServiceImpl(DAOSystem.FILE_SYSTEM);
        cs.save(config);

        Configuration config2 = cs.load();

        for (Node node : config2.getNodes()) {
            node.getContainers().forEach(x -> System.out.println("N! " + x));
        }
        for (Service service : config2.getServices()) {
            service.getContainers().forEach(x -> System.out.println("S! " + x));
        }
    }
}
