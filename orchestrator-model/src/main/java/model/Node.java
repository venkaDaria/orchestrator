package model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    private Set<Role> roles;
    private List<Container> containers;
    
    public Node() {
        this.containers = new ArrayList<>();
    }
    
    public Set<Role> getRoles() {
        return roles;
    }    
    
    public boolean hasRoles() {
        return roles != null && !roles.isEmpty();
    }
    
    public void setRoles(final Collection<Role> roles) {
        this.roles = new HashSet<>(roles);
    }
    
    public List<Container> getContainers() {
        return containers;
    }
    
    public boolean hasContainers() {
        return containers != null && !containers.isEmpty();
    }

    public void addContainer(final Container container) {
    	if (container != null && (!container.hasNode() || !container.getNode().equals(this))) {
            container.setNode(this);
        }
    }
    
    public void addContainers(Iterable<Container> collection) {
        for (Container cont : containers) {
        	addContainer(cont);
        }
    }
    
    public void removeContainer(final Container container) {
    	if (container != null && container.hasNode() && container.getNode().equals(this)) {
            container.setNode(null);
        } 
    }
    
    public void removeContainers(Iterable<Container> collection) {
        for (Container cont : containers) {
        	removeContainer(cont);
        }
    }
    
    public void clearContainers() {
        for (Container cont : containers) {
            removeContainer(cont);
        }
    }

    public Node copy() {
        Node node = new Node();
        
        node.setRoles(roles);
        
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
