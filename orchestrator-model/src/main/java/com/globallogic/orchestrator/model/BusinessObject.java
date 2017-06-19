package com.globallogic.orchestrator.model;

import java.util.Collections;
import java.util.Set;

public abstract class BusinessObject implements Formattable {

	@Deprecated
	@Override
	public String toString() {
		return asFormattedString();
	}

	public <T> Set<T> getUnmodifiableSet(Set<T> set) {
		return Collections.unmodifiableSet(set);
	}
}
