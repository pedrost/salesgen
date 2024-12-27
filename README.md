# SalesGen - Dynamic Startup Presentation Generator

SalesGen is a Java-based application built with Spring Boot and Apache POI that generates dynamic presentations for startups using the OpenAI API. By providing a simple prompt describing the startup, the app automatically creates a presentation with sections like:

- Problem Statement
- Graphs/Visuals
- Company Introduction
- Team Overview

## Features

- **Generate Presentations**: Automatically create a professional presentation for your startup.
- **Customizable Input**: Provide a simple prompt to define the startup details.
- **OpenAI Integration**: Leverage the power of the OpenAI API for content generation.
- **Graphs and Visuals**: Dynamically generate data visualizations for your startup's story.
- **Swagger UI**: Built-in Swagger interface for API testing and exploration.

## Prerequisites

Before you start, you'll need to have the following tools and services:

- **Java 11+** (JDK)
- **Maven** (for dependency management)
- **Spring Boot** (for app framework)
- **Apache POI** (for generating PowerPoint presentations)
- **OpenAI API Key** (for content generation)

## Getting Started

### Step 1: Clone the Repository

```
git clone https://github.com/your-username/SalesGen.git
cd SalesGen
```

### Step 2: Set Up OpenAI Credentials

To interact with the OpenAI API, you'll need to set up your OpenAI credentials.

1. Create an account on [OpenAI](https://openai.com/).
2. Obtain your API key.
3. Set your OpenAI API key in the environment variables or in a configuration file.

You can set the environment variable as follows:

```
export OPENAI_API_KEY="your_openai_api_key"
```

### Step 3: Build the Application

If you haven't already installed Maven, you can do so by following the installation instructions on [Maven's official website](https://maven.apache.org/install.html).

Once Maven is installed, build the project:

```
mvn clean install
```

### Step 4: Run the Application

Start the application using Spring Boot:

```
mvn spring-boot:run
```

### Step 5: Access Swagger UI

Once the application is running, you can access the Swagger UI for API testing at:

```
http://localhost:8080/swagger-ui.html
```

This provides an interactive interface for you to test the API endpoints and generate presentations.

## API Usage

The main API endpoint for generating the presentation is:

### `POST /api/generate-presentation`

**Request Body:**

```
{
"startupDescription": "Describe your startup here."
}
```

**Response:**

```
{
"presentationUrl": "http://localhost:8080/generated_presentation/filename.pptx"
}
```

This endpoint will generate a PowerPoint file and provide a link to download the generated presentation.

## Folder Structure


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Contributing

Feel free to fork this repository and submit issues or pull requests for improvements!

---

Enjoy using **SalesGen** to create stunning, dynamic presentations for your startup!
