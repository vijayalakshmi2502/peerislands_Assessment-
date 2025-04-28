package CodeBase;

import java.io.*;
import java.util.*;

public class CodeReader {

   
    public static List<String> readJavaFiles(String folderPath) {
        List<String> codeList = new ArrayList<>();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            System.out.println("Folder not found.");
            return codeList;
        }

       
        String[] targetClasses = { "Customer.java" ,"Actor.java","CategoryController.java"}; 

       
        findSpecificJavaFiles(folder, codeList, targetClasses);
        return codeList;
    }

   
    private static void findSpecificJavaFiles(File dir, List<String> codeList, String[] targetClasses) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                findSpecificJavaFiles(file, codeList, targetClasses); 
            } else if (file.getName().endsWith(".java")) {
                
                for (String targetClass : targetClasses) {
                    if (file.getName().equalsIgnoreCase(targetClass)) {
                        try {
                            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                            codeList.add(content); 
                            System.out.println("Found Java file: " + file.getAbsolutePath());
                            
                        } catch (IOException e) {
                            System.out.println("Error reading file: " + file.getName());
                        }
                    }
                }
            }
        }
    }
}
