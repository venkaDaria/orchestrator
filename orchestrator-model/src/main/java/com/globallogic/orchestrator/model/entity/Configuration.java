package com.globallogic.orchestrator.model.entity;

import com.globallogic.orchestrator.model.base.ConfigurationBase;

public class Configuration extends ConfigurationBase {

    @Override
    public String asFormattedString() {
        return "ConfigurationBase [nodes=" + getNodes() + ", services=" + getServices() + "]";
    }
}