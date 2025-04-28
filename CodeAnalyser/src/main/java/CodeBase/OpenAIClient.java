package CodeBase;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.ContentType;

import org.json.JSONObject;
import org.json.JSONArray;

public class OpenAIClient {
    private static final String API_KEY = "tgp_v1_eyuJ7nvKscsSIew4Oz431gtHMOxbE2Idfp_XdhAf-B0"; 
    private static final String ENDPOINT = "https://api.together.xyz/v1/chat/completions";
    private static final String MODEL = "mistralai/Mistral-7B-Instruct-v0.2"; // ✅ correct model name

    public static String extractKnowledge(String code) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(ENDPOINT);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", "Bearer " + API_KEY);

            JSONArray messages = new JSONArray();

            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", "You are a Java code analysis assistant. Provide useful insights such as purpose, key methods, and complexity.");

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", "Analyze this Java code and extract purpose, key methods, and complexity:\n\n" + code);

            messages.put(systemMessage);
            messages.put(userMessage);

           
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", MODEL);
            requestBody.put("messages", messages);

            request.setEntity(new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON));

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                
                System.out.println("API Response Status Code: " + statusCode);
                System.out.println("API Response Body: " + responseBody);

                if (statusCode != 200) {
                    System.out.println("❌ API Error (" + statusCode + "): " + responseBody);
                    return "Error: API call failed.";
                }

                JSONObject responseJson = new JSONObject(responseBody);

                if (!responseJson.has("choices")) {
                    System.out.println("❌ Invalid response (missing 'choices'): " + responseBody);
                    return "Error: No analysis returned.";
                }

                
                String knowledge = responseJson
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");

                System.out.println("Extracted Knowledge: " + knowledge);  
                return knowledge;
            }
        }
    }
}
