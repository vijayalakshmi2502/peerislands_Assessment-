
package CodeBase;
import org.json.JSONArray;


import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    
    public static void writeAnalysisToFile(String content) {
       
        File outputFile = new File("output/knowledge.json");

        JSONArray allAnalysis = new JSONArray();

       
        JSONObject fileAnalysis = new JSONObject();
        fileAnalysis.put("content", content);

        
        allAnalysis.put(fileAnalysis);

       
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(allAnalysis.toString(4));  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       
        String combinedKnowledge = "Example analysis content.";

       
        writeAnalysisToFile(combinedKnowledge);
    }
}
