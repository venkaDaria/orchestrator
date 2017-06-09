package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Service {
    private String name;
    private ImageReference image;
    private Set<Volume> volumes;
    private Set<Port> ports;
    private Set<Role> roles;
    private List<Container> containers;

    public Service() {
        this.volumes = new HashSet<>();
        this.ports = new HashSet<>();
        this.roles = new HashSet<>();
        this.containers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    
    public boolean hasName() {
        return name != null;
    }

    public ImageReference getImage() {
        return image;
    }

    public void setImage(final ImageReference image) {
        this.image = image.copy();
    }
    
    public boolean hasImage() {
        return image != null;
    }

    public Set<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(final Set<Volume> volumes) {
        this.volumes = new HashSet<>(volumes);
    }    
    
    public boolean hasVolumes() {
        return volumes!= null && !volumes.isEmpty();
    }

    public Set<Port> getPorts() {
        return ports;
    }

    public void setPorts(final Set<Port> ports) {
        this.ports = new HashSet<>(ports);
    }
    
    public boolean hasPorts() {
        return ports != null && !ports.isEmpty();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = new HashSet<>(roles);
    }
    
    public boolean hasRoles() {
        return roles != null && !roles.isEmpty();
    }

    public List<Container> getContainers() {
        return containers;
    }
    
    public boolean hasContainers() {
        return containers != null && !containers.isEmpty();
    }

    public void addContainer(final Container container) {
        if (!container.hasService() || !container.getService().equals(this)) {
            container.setService(this);
        }
        if (!containers.contains(container)) {
            container.setStatus(Status.STOPPED);
            containers.add(container);            
        }
    }

    public void removeContainer(final Container container) {
        if (container.hasService() && container.getService().equals(this)) {
            container.setService(null);
        } if (containers.contains(container)) {
            container.setStatus(Status.NONE);
            containers.remove(container);
        }
    }

    public void clearContainers() {
        for (Container cont : containers) {
            removeContainer(cont);
        }
    }

    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        for (Container cont : containers) {
            if (!nodes.contains(cont.getNode())) {
                nodes.add(cont.getNode());
            }
        }
        return nodes;
    }

    public Service copy() {
        Service service = new Service();
        service.setName(name);
        service.setImage(image.copy());

        service.setPorts(ports.stream().map(Port::copy).collect(Collectors.toSet()));
        service.setRoles(roles.stream().map(Role::copy).collect(Collectors.toSet()));
        service.setVolumes(volumes.stream().map(Volume::copy).collect(Collectors.toSet()));

        for (final Container cont : containers) {
            Container container = new Container();
            container.setService(service);
            container.setNode(cont.getNode().copy());
        }
        return service;
    }

    @Override
    public String toString() {
        return "Service [name=" + name + ", image=" + image + ", volumes=" + volumes + ", ports=" + ports + ", roles="
                + roles + "]";
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
