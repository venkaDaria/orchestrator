package model.valueobject;

import model.BusinessObject;

public abstract class ValueObject<T> extends BusinessObject {
	public abstract T getValue();
}
