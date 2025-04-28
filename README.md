#Peerislands_Assessment:
This project offers an automated approach for analyzing Java code files using a Large Language Model (LLM). The goal is to extract meaningful insights from Java code, such as project purpose, method signatures, code complexity, and overall structure, to improve understanding, documentation, and code quality evaluation.

Approach:
The project centers around reading specific Java files and utilizing a powerful LLM model hosted via the Together API. Unlike offline NLP libraries like spaCy, this project leverages cloud-based AI models to provide deeper code comprehension and more accurate structural insights.

Steps:
Reading Java Files: Specific Java files (Customer.java, MainController.java, FilmController.java) are recursively searched and read from the local project directory.

Sending Code to LLM: The extracted code is sent to the Mistral-7B-Instruct-v0.2 model using a secure HTTP API request.

Knowledge Extraction: The LLM analyzes the code and extracts insights like the project's purpose, key classes and methods, method descriptions, and code complexity observations.

Saving Results: The structured output is stored in a machine-readable knowledge.json file inside the output/ directory.

Methodologies Employed:
LLM-Based Analysis: Instead of simple text parsing, a state-of-the-art LLM model (Mistral-7B) is used to deeply analyze the code and provide high-quality semantic insights.

Secure API Communication: HTTP communication with authentication ensures secure and reliable interaction with the external LLM API.

Structured Output: The results are formatted as JSON, making it easy to further consume, integrate, or visualize the extracted knowledge.

Best Practices:
Token Limit Management: The program ensures that input data is efficiently handled to avoid exceeding token limits during LLM interaction.

Error Handling: Clear error messages and fallback mechanisms are implemented if API responses fail or rate limits are hit.

Modular Code: Code is cleanly separated into modules for reading Java files (CodeReader.java) and calling the LLM API (OpenAIClient.java).

Consistency: Output is structured consistently to allow easy parsing for downstream applications or documentation generation.

Assumptions:
Specific Files Targeted: Only specific Java files (Customer.java, MainController.java, FilmController.java) are selected for analysis.

Project Standardization: The Java code is assumed to follow standard Java coding practices for the LLM to accurately infer structure and functionality.

External API Dependency: Internet access and a valid API key for Together API are required for functioning.

Limitations:
API Dependency: Requires network availability and an API key. Analysis cannot be done completely offline.

Cost: Depending on API usage, calling external LLM services might incur costs.

Token Limits: Very large Java files may need to be split manually or optimized to avoid token overflow errors.

Conclusion:
This project provides a powerful, AI-driven solution for automating Java code analysis using modern Large Language Models. While it depends on external services, it offers a deeper level of understanding compared to traditional local parsing techniques and forms a strong foundation for building advanced developer tools or automated documentation systems.
