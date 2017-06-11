package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Configuration {
    private List<Node> nodes;
    private List<Service> services;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(final List<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }

    public boolean hasNodes() {
        return nodes != null && !nodes.isEmpty();
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(final List<Service> services) {
        this.services = new ArrayList<>(services);
    }

    public boolean hasServices() {
        return services != null && !services.isEmpty();
    }

    public Configuration copy() {
        Configuration con = new Configuration();
        con.setNodes(nodes.stream().map(Node::copy).collect(Collectors.toList()));
        con.setServices(services.stream().map(Service::copy).collect(Collectors.toList()));
        return con;
    }
}