package eu.test.json_subclass;

public class Lion extends Animal {
	private String action;

	// Empty constructor needed for deserialization
	public Lion() {
	}

	public Lion(String name) {
		this.name = name;
		this.endangered = true;
		this.action = "running";
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.delete(sb.length() - 1, sb.length());
		sb.append(", action=" + action + "}");
		return sb.toString();
	}

}