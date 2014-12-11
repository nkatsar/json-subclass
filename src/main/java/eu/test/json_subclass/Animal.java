package eu.test.json_subclass;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

// Simplified version of http://www.studytrails.com/java/json/java-jackson-Serialization-polymorphism.jsp

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Lion.class, name = "lion"),
		@Type(value = Elephant.class, name = "elephant") })
public abstract class Animal {
	String name;
	boolean endangered;

	public void setName(String name) {
		this.name = name;
	}

	public void setEndangered(boolean endangered) {
		this.endangered = endangered;
	}

	public String getName() {
		return name;
	}

	public boolean isEndangered() {
		return endangered;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " {name=" + name + ", endangered="
				+ endangered + "}";
	}

}