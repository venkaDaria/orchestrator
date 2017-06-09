package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Node {
    private Set<Role> roles;
    private List<Container> containers;
    
    public Node() {
        this.roles = new HashSet<>();
        this.containers = new ArrayList<>();
    }
    
    public Set<Role> getRoles() {
        return roles;
    }    
    
    public boolean hasRoles() {
        return roles != null && !roles.isEmpty();
    }
    
    public void setRoles(final Set<Role> roles) {
        this.roles = new HashSet<>(roles);
    }
    
    public List<Container> getContainers() {
        return containers;
    }
    
    public boolean hasContainers() {
        return containers != null && !containers.isEmpty();
    }

    public void addContainer(final Container container) {
        if (!container.hasNode() || !container.getNode().equals(this)) {
            container.setNode(this);
        } if (!containers.contains(container)) {
            containers.add(container);
        }
    }
    
    public void removeContainer(final Container container) {
        if (container.hasNode() && container.getNode().equals(this)) {
            container.setNode(null);
        } if (containers.contains(container)) {
            containers.remove(container);
        }
    }
    
    public void clearContainers() {
        for (Container cont : containers) {
            removeContainer(cont);
        }
    }

    public Node copy() {
        Node node = new Node();
        node.setRoles(roles.stream().map(Role::copy).collect(Collectors.toSet()));
        for (final Container cont : containers) {
            Container container = new Container();
            container.setService(cont.getService().copy());
            container.setNode(node);
        }
        return node;
    }

    @Override
    public String toString() {
        return "Node [roles=" + roles + "]";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return this == obj;
    }
}
