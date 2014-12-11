package eu.test.json_subclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Testing JSON Conversions for polymorphic types
 *
 */
public class App {
	public static void main(String[] args) {
		final String outputFile = "output.json";

		List<Animal> myList = new ArrayList<Animal>();
		myList.add(new Lion("Simba"));
		// myList.add(new Lion("Nala"));
		myList.add(new Elephant("Dumbo"));
		// myList.add(new Elephant("Lucy"));
		AnimalList wrapperWrite = new AnimalList(myList);

		File jsonDocument = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonDocument = new File(outputFile);

			// Test writing using wrapper
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDocument,
					wrapperWrite);
			System.out.println("Written to file: "
					+ jsonDocument.getAbsolutePath() + "\ndata: "
					+ wrapperWrite);

			// Test reading using wrapper
			AnimalList wrapperRead = mapper.readValue(new FileInputStream(
					jsonDocument), AnimalList.class);
			System.out
					.println("Read from file: "
							+ jsonDocument.getAbsolutePath() + "\ndata: "
							+ wrapperRead);

			// Test reading using raw list
			List<Animal> jsonList = mapper.readValue(new FileInputStream(
					"demo.json"), new TypeReference<List<Animal>>() {
			});
			System.out.println("Read from demo.json \ndata: " + jsonList);

			jsonDocument = new File(outputFile);
			// Test writing using raw list
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDocument,
					jsonList);
			System.out.println("Written to file: "
					+ jsonDocument.getAbsolutePath() + "\ndata: "
					+ jsonList);

		} catch (JsonGenerationException e) {
			System.out.println("Could not generate JSON: " + e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Invalid JSON Mapping: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "
					+ jsonDocument.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("File I/O error: ");
		} finally {
			if (jsonDocument != null)
				jsonDocument.delete();
		}
	}
}
