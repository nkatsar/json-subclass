package eu.test.json_subclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Testing JSON Conversions for polymorphic types
 *
 */
public class App {
	public static void main(String[] args) {
		final String demoFile = "demo.json";

		List<Animal> myList = new ArrayList<Animal>();
		myList.add(new Lion("Simba"));
		// myList.add(new Lion("Nala"));
		myList.add(new Elephant("Dumbo"));
		// myList.add(new Elephant("Lucy"));

		// Serialization/deserialization using wrapper list
		AnimalList wrapperWrite = new AnimalList(myList);

		ObjectMapper mapper = new ObjectMapper();
		String outputJson = null;

		try {
			// Test writing using wrapper
			outputJson = mapper.writeValueAsString(wrapperWrite);
			System.out.println("Writing using wrapper object:\nJSON: "
					+ outputJson);

			// Test reading using wrapper
			AnimalList wrapperRead = mapper.readValue(outputJson,
					AnimalList.class);
			System.out.println("Reading using wrapper object:\nObject: "
					+ wrapperRead);

			JavaType listType = mapper.getTypeFactory()
					.constructCollectionType(List.class, Animal.class);

			// Test reading using generic list
			List<Animal> jsonList = mapper.readValue(new FileInputStream(
					demoFile), listType);
			System.out
					.println("Reading using generic list with type:\nObject: "
							+ jsonList);

			// Test writing using generic list
			outputJson = mapper.writeValueAsString(jsonList);
			System.out
					.println("Writing using generic list without type:\nJSON: "
							+ outputJson);

			outputJson = mapper.writerWithType(listType).writeValueAsString(
					jsonList);
			System.out.println("Writing using generic list with type:\nJSON: "
					+ outputJson);

			// Test reading using array
			Animal[] jsonArray = mapper.readValue(
					new FileInputStream(demoFile), Animal[].class);
			System.out.println("Reading using array:\nObject: "
					+ Arrays.toString(jsonArray));

			// Test writing using array
			outputJson = mapper.writeValueAsString(jsonArray);
			System.out.println("Writing using array:\nJSON: " + outputJson);

		} catch (JsonGenerationException e) {
			System.out.println("Could not generate JSON: " + e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Invalid JSON Mapping: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + demoFile);
		} catch (IOException e) {
			System.out.println("File I/O error: ");
		}
	}
}
