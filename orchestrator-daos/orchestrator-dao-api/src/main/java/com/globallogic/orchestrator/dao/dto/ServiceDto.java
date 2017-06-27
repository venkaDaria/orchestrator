package com.globallogic.orchestrator.dao.dto;

import java.util.HashSet;
import java.util.Set;

public class ServiceDto {

    private String name;

    private String image;

    private Set<String> roles;

    private Set<String> ports;

    private Set<String> volumes;

    public ServiceDto() { }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(final Set<String> roles) {
        this.roles = new HashSet<>(roles);
    }

    public Set<String> getPorts() {
        return ports;
    }

    public void setPorts(final Set<String> ports) {
        this.ports = new HashSet<>(ports);
    }

    public Set<String> getVolumes() {
        return volumes;
    }

    public void setVolumes(final Set<String> volumes) {
        this.volumes = new HashSet<>(volumes);
    }

    @Override
    public String toString() {
        return "ServiceDto [name=" + name +
                ", image=" + image + ", roles=" + roles +
                ", ports=" + ports + ", volumes=" + volumes + "]";
    }
}
