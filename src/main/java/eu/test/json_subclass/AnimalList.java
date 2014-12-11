package eu.test.json_subclass;

import java.util.ArrayList;
import java.util.List;

public class AnimalList {
	private List<Animal> animals = new ArrayList<Animal>();
	
	public AnimalList() {
	}

	public AnimalList(List<Animal> animals) {
		this.animals = animals;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Animal a: animals) {
			sb.append(a.toString());
			sb.append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		return getClass().getSimpleName() + " [" + sb.toString() + "]";
	}

}
