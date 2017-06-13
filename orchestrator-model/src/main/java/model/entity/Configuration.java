package model.entity;

import model.base.ConfigurationBase;

public class Configuration extends ConfigurationBase {

	@Override
	public String asFormattedString() {
		return "ConfigurationBase [nodes=" + getNodes() + ", services=" + getServices() + "]";
	}

	@Override
	public Object getIdentity() {
		return new Object[] { getNodes(), getServices() };
	}
}