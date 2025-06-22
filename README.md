# Demo AI Project - ChatGPT Integration

This project is a Spring Boot application that demonstrates how to integrate with OpenAI's ChatGPT using the Spring AI library.

## Prerequisites

- Java 17 or later
- Maven
- An active OpenAI API Key

## Setup

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd demo-ai
    ```

2.  **Configure OpenAI API Key:**
    You need to provide your OpenAI API key to the application. You can do this in one of two ways:

    *   **Option A: Environment Variable (Recommended)**
        Set the `SPRING_AI_OPENAI_API_KEY` environment variable:
        ```bash
        export SPRING_AI_OPENAI_API_KEY="your_openai_api_key_here"
        ```
        On Windows, use `set SPRING_AI_OPENAI_API_KEY="your_openai_api_key_here"` in Command Prompt or `$env:SPRING_AI_OPENAI_API_KEY="your_openai_api_key_here"` in PowerShell.

    *   **Option B: `application.properties` file**
        Open the `src/main/resources/application.properties` file and replace `YOUR_API_KEY` with your actual OpenAI API key:
        ```properties
        spring.ai.openai.api-key=your_openai_api_key_here
        ```
        **Note:** Be careful not to commit your API key to version control if you use this method in a shared repository.

3.  **Choose a Model (Optional):**
    The application is configured to use `gpt-3.5-turbo` by default. You can change this in `src/main/resources/application.properties`:
    ```properties
    spring.ai.openai.chat.options.model=gpt-4 # Or any other model you have access to
    ```

## Running the Application

1.  **Build the project:**
    ```bash
    ./mvnw clean package
    ```
    (On Windows, use `mvnw.cmd clean package`)

2.  **Run the Spring Boot application:**
    ```bash
    java -jar target/demo-ai-0.0.1-SNAPSHOT.war
    ```
    Alternatively, you can run directly using the Maven Spring Boot plugin:
    ```bash
    ./mvnw spring-boot:run
    ```

The application will start on the default port (usually 8080).

## Using the API

You can interact with the ChatGPT integration through a POST request to the `/api/v1/chat` endpoint.

**Endpoint:** `POST /api/v1/chat`

**Request Body (JSON):**
```json
{
  "prompt": "What is the capital of France?"
}
```

**Example using Postman:**

1.  Open Postman.
2.  Create a new request:
    *   Set the method to `POST`.
    *   Set the URL to `http://localhost:8080/api/v1/chat`.
3.  Go to the "Body" tab:
    *   Select "raw".
    *   Choose "JSON" from the dropdown.
    *   Paste the request body:
        ```json
        {
          "prompt": "Tell me a joke about programming."
        }
        ```
4.  Click "Send".

**Example Response (Success - 200 OK):**
```json
{
  "response": "Why was the JavaScript developer sad? Because he didn't Node how to Express himself!"
}
```

**Example Response (Error - 400 Bad Request if prompt is missing):**
```json
{
  "error": "Prompt cannot be empty."
}
```

**Example Response (Error - 500 Internal Server Error if API key is invalid or other server issues):**
```json
{
  "error": "Error: Could not get response from AI service."
}
```

This indicates an issue with the OpenAI API call, often due to an invalid or missing API key, or network problems. Check the application logs for more details.
