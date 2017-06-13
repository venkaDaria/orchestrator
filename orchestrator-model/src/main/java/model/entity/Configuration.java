package model.entity;

public class Configuration extends ConfigurationBase {

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Configuration other = (Configuration) obj;
		return getIdentity() == null && other.getIdentity() == null
				|| getIdentity() != null && getIdentity().equals(other.getIdentity());
	}

	@Override
	public String asFormattedString() {
		return "ConfigurationBase [nodes=" + getNodes() + ", services=" + getServices() + "]";
	}

	@Override
	public Object getIdentity() {
		return new Object[] { getNodes(), getServices() };
	}
}