package com.globallogic.orchestrator.dao.dto;

import java.util.HashSet;
import java.util.Set;

public class NodeDto {

    private String name;

    private Set<String> roles;

    public NodeDto() { }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(final Set<String> roles) {
        this.roles = new HashSet<>(roles);
    }
}
