package model;

public class Container {
    private Status status;
    private transient Node node;
    private transient Service service;
    
    public Container() {
        status = Status.NONE;
    }

    public Status getStatus() {
        return status;
    }
    
    public void setStatus(final Status status) {
        this.status = status;
    }
    
    public boolean hasStatus() {
        return status != null;
    }
    
    public Node getNode() {
        return node;
    }
    
    public void setNode(final Node node) {
        if (hasNode()) {
            this.node.getContainers().remove(this);
        }
        this.node = node;
        if (hasNode()) {    
            this.node.getContainers().add(this);
        }        
    }
    
    public boolean hasNode() {
        return node != null;
    }
    
    public Service getService() {
        return service;
    }
    
    public void setService(final Service service) {
        if (hasService()) {
            this.service.getContainers().remove(this);
        }
        this.service = service;
        if (hasService()) {    
            this.service.getContainers().add(this);
        }        
    }
    
    public boolean hasService() {
        return service != null;
    }
    
    public Container copy() {
        Container cont = new Container();
        cont.setNode(node.copy());
        cont.setService(service.copy());
        cont.setStatus(status);
        return cont;
    }

    @Override
    public String toString() {
        return "Container [status=" + status + ", node=" + node + ", service=" + service + "]";
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
