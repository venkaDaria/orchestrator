package com.globallogic.orchestrator.model;

import java.util.Objects;

public abstract class Entity extends BusinessObject {
	public abstract Object getIdentity();

	@Override
	public int hashCode() {
		return Objects.hashCode(getIdentity());
	}

	@Override
	public boolean equals(final Object obj) {
		if (getClass() != obj.getClass())
			return false;
		return Objects.equals(getIdentity(), ((Entity) obj).getIdentity());
	}
}
