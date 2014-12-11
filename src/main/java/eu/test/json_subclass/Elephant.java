package eu.test.json_subclass;

import java.util.Arrays;
import java.util.List;

public class Elephant extends Animal {
	private List<Double> table;

	// Empty constructor needed for deserialization
	public Elephant() {
	}

	public Elephant(String name) {
		this.name = name;
		this.endangered = false;
		Double[] array = { 1.0, 2.0, 3.0 };
		this.table = Arrays.asList(array);
	}

	public List<Double> getTable() {
		return table;
	}

	public void setTable(List<Double> table) {
		this.table = table;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.delete(sb.length() - 1, sb.length());
		sb.append(", table=" + table + "}");
		return sb.toString();
	}

}