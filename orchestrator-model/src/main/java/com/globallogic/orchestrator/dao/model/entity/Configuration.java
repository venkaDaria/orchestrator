package com.globallogic.orchestrator.dao.model.entity;

import com.globallogic.orchestrator.dao.model.base.ConfigurationBase;

public class Configuration extends ConfigurationBase {

	@Override
	public String asFormattedString() {
		return "ConfigurationBase [nodes=" + getNodes() + ", services=" + getServices() + "]";
	}
}