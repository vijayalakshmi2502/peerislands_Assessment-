
package CodeBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    public static void writeAnalysisToFile(String extractedKnowledge) {
        // Create the output directory if it doesn't exist
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File outputFile = new File(outputDir, "knowledge.json");

        // Create structured JSON
        JSONObject finalJson = new JSONObject();

        // 1. Project Overview (First paragraph of extracted knowledge)
        String[] parts = extractedKnowledge.split("Key methods in this class include:");
        String projectOverview = parts[0].trim();

        finalJson.put("project overview", projectOverview);

        // 2. Functions
        JSONArray functionsArray = new JSONArray();
        if (parts.length > 1) {
            String[] methods = parts[1].split("\n");

            for (String method : methods) {
                method = method.trim();
                if (method.startsWith("1.") || method.startsWith("2.") || method.startsWith("3.") || method.startsWith("4.") ||
                    method.startsWith("5.") || method.startsWith("6.") || method.startsWith("7.") || method.startsWith("8.")) {
                    int colonIndex = method.indexOf(":");
                    if (colonIndex > 0) {
                        String functionName = method.substring(method.indexOf('`') + 1, method.indexOf('`', method.indexOf('`') + 1));
                        String description = method.substring(colonIndex + 1).trim();

                        JSONObject functionObject = new JSONObject();
                        functionObject.put("function name", functionName);
                        functionObject.put("description", description);

                        functionsArray.put(functionObject);
                    }
                }
            }
        }
        finalJson.put("functions", functionsArray);

        // 3. Complexity
        JSONObject complexityObject = new JSONObject();
        if (extractedKnowledge.toLowerCase().contains("complexity of this code")) {
            complexityObject.put("score", "2/10");  // You can improve later by making it dynamic.
            complexityObject.put("description", "Relatively low complexity, mainly consists of simple getters/setters and equality methods.");
        } else {
            complexityObject.put("score", "Unknown");
            complexityObject.put("description", "Could not determine complexity from extracted text.");
        }
        finalJson.put("complexity", complexityObject);

        // Write the JSON to file
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(finalJson.toString(4)); // Pretty print
            System.out.println("✅ Knowledge written successfully to " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}