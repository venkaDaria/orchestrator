package com.globallogic.orchestrator.model.entity;

import com.globallogic.orchestrator.exception.NodeValidationException;
import com.globallogic.orchestrator.model.base.NodeBase;

public class Node extends NodeBase {

    @Override
    public String asFormattedString() {
        return "Node [name=" + getName() + ", roles=" + getRoles() + "]";
    }

    @Override
    public Object getIdentity() {
        return getName();
    }

    @Override
    public void addContainer(final Container container) {
        if (container == null) {
            throw new NodeValidationException("Can't add container");
        }
        if (!container.hasNode() || !container.getNode().equals(this)) {
            container.setNode(this);
        } else {
            super.addContainer(container);
        }
    }
}
