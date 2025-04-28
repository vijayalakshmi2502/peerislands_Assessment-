package CodeBase;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       
        String path = "C:/Users/lahar/OneDrive/Pictures/Lahari Kancharla/ProjectCloning/SakilaProject";

       
        List<String> files = CodeReader.readJavaFiles(path);
        StringBuilder combinedKnowledge = new StringBuilder();

       
        for (String code : files) {
            try {
                String knowledge = OpenAIClient.extractKnowledge(code);
                combinedKnowledge.append(knowledge).append("\n\n");
            } catch (Exception e) {
                System.out.println("Error analyzing code: " + e.getMessage());
            }
        }

        
        JsonWriter.writeAnalysisToFile(combinedKnowledge.toString());  
    }
}
