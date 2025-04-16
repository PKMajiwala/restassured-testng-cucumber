
# 🚀 RestAssured + TestNG + Cucumber API Automation Framework

A robust API test automation framework using **Java**, **RestAssured**, **Cucumber (BDD)**, **TestNG**, **Allure Reports**, with advanced features like **WireMock**, **Docker**, **Jenkins CI/CD**, **Test Tagging**, **Multi-Environment Support**, and **Email Reports**.

---

## 📁 Project Structure

```
restassured-testng-cucumber/
│
├── config/
│   ├── config.properties       # Default environment config
│   ├── qa.properties           # QA environment config
│
├── src/
│   ├── main/java/utils/
│   │   └── ConfigReader.java   # Loads environment-specific configs
│   │
│   └── test/
│       ├── java/
│       │   ├── runners/
│       │   │   └── TestRunner.java       # TestNG + Cucumber runner
│       │   ├── stepdefinitions/
│       │   │   └── GetUserSteps.java     # Step definitions for feature
│       │   └── mocks/
│       │       └── WireMockServerInitializer.java  # Mock server setup
│       │
│       └── resources/features/
│           └── GetUser.feature           # Feature file with @smoke tag
│
├── Dockerfile                            # Docker container setup
├── docker-compose.yml                    # Docker Compose config
├── Jenkinsfile                           # Jenkins CI/CD pipeline
├── testng.xml                            # TestNG suite config
└── README.md
```

---

## ✅ Features

- 🔗 REST API testing with **RestAssured**
- ✍️ BDD-style test cases with **Cucumber + Gherkin**
- ⚙️ **TestNG** runner with parallel execution
- 📊 **Allure Reports** for visual test results
- 🧬 Mock APIs using **WireMock**
- ☁️ Environment-specific config (`dev`, `qa`, `staging`)
- 🧪 Scenario tagging (e.g., `@smoke`, `@regression`)
- 🐳 Dockerized test execution
- 📤 Jenkins integration with 📧 email report notifications

---

## 🧪 Sample Feature

`src/test/resources/features/GetUser.feature`

```gherkin
@smoke
Feature: Get user details

  Scenario: Verify single user details
    Given the API is up and running
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response should contain the user with id 2
```

---

## ⚙️ ConfigReader (Environment Support)

`src/main/java/utils/ConfigReader.java`

```java
ConfigReader.loadConfig(System.getProperty("env", "config"));
baseURI = ConfigReader.getProperty("base.url");
```

To run with `qa` env:
```bash
mvn test -Denv=qa
```

---

## 🧬 Mock API with WireMock

```java
WireMockServer mock = WireMockServerInitializer.startMockServer();
// Your test code here...
mock.stop();
```

Mock endpoint: `GET http://localhost:8089/api/mock-user`  
Returns:
```json
{
  "id": 999,
  "name": "Mock User"
}
```

---

## 🐳 Run with Docker

**Dockerfile**

```dockerfile
FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean install
CMD ["mvn", "test"]
```

**docker-compose.yml**
```yaml
version: '3.8'
services:
  api-tests:
    build: .
    container_name: api-test-container
    volumes:
      - .:/app
    working_dir: /app
```

To run:
```bash
docker-compose up --build
```

---

## 🧪 Test Tagging

In feature files:

```gherkin
@regression
Scenario: Another test scenario
```

Run only `@smoke` tests:
```bash
mvn test -Dcucumber.filter.tags=@smoke
```

---

## 🧪 Run & View Allure Report

```bash
mvn clean test
allure serve target/allure-results
```

---

## 🔁 Jenkins Pipeline (Jenkinsfile)

```groovy
pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean test'
      }
    }
    stage('Report') {
      steps {
        sh 'allure generate target/allure-results --clean -o target/allure-report'
        archiveArtifacts 'target/allure-report/**'
      }
    }
  }
  post {
    always {
      emailext(
        to: 'qa-team@example.com',
        subject: "API Test Report",
        body: "The test suite has completed. Check Allure Report.",
        attachmentsPattern: 'target/allure-report/index.html'
      )
    }
  }
}
```

---

## 🔑 CLI Summary

| Action | Command |
|--------|---------|
| Run with `qa` config | `mvn test -Denv=qa` |
| Run `@smoke` tests | `mvn test -Dcucumber.filter.tags=@smoke` |
| Generate Allure report | `allure serve target/allure-results` |
| Docker execution | `docker-compose up --build` |

---
