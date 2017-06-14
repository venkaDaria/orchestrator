package model;

public abstract class ValueObject<T> extends BusinessObject {
	public abstract T getValue();

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		ValueObject<T> other = (ValueObject<T>) obj;
		return getValue().equals(other.getValue());
	}
}
