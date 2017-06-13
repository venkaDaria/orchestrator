package model;

public abstract class BusinessObject implements Formattable{

	@Deprecated
	@Override
	public String toString() {
		return asFormattedString();
	}
}
