package com.globallogic.orchestrator.model.entity;

import com.globallogic.orchestrator.exception.ServiceValidationException;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.base.ServiceBase;

public class Service extends ServiceBase {
    @Override
    public String asFormattedString() {
        return "Service [name=" + getName() + ", image=" + getImage() + ", volumes=" + getVolumes() + ", ports="
                + getPorts() + ", roles=" + getRoles() + "]";
    }

    @Override
    public Object getIdentity() {
        return getName();
    }

    @Override
    public void addContainer(final Container container) {
        if (container == null) {
            throw new ServiceValidationException("Can't add container");
        }
        if (!container.hasService() || !container.getService().equals(this)) {
            container.setStatus(Status.STOPPED);
            container.setService(this);
        } else {
            super.addContainer(container);
        }
    }
}
