# ClusteredData Warehouse

Welcome to the ClusteredData Warehouse project! This system is designed to accept and persist FX deals details for analysis. Below, you will find information on how to set up and use the system.

## Table of Contents

- [Assignment Overview](#assignment-overview)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Request Logic](#request-logic)
- [Deliverables](#deliverables)

## Assignment Overview

This project is part of a scrum team developing a data warehouse for Bloomberg to analyze FX deals. The main focus is on accepting deal details and persisting them into the database. The request logic includes dealing with various fields and implementing validation rules. The system should prevent the import of the same request twice, and once imported, the rows should be saved in the database.

## Tech Stack

The project utilizes the following technologies:

- **Java:** Version 17
- **Spring Boot:** Version 3.1.0
- **Spring Data JPA:** For data access
- **Spring Actuator:** For monitoring and managing the application
- **Spring Web:** For building web applications
- **MySQL Connector:** For MySQL database connectivity
- **JUnit:** Version 4.13.2 for testing
- **Springdoc OpenAPI:** Version 2.1.0 for OpenAPI documentation

## Getting Started

To get started with the ClusteredData Warehouse project, follow these steps:

1. Clone the repository to your local machine.
2. Ensure you have Java 17 installed.
3. Set up a MySQL database and update the `application.properties` file with the appropriate database configurations.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `java -jar target/warehouse-0.0.1-SNAPSHOT.jar`.

## Request Logic

The system follows the specified request logic:

- Request Fields: Deal Unique Id, From Currency ISO Code, To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency.
- Validate row structure: Implement basic validations for missing fields and type formats.
- Prevent importing the same request twice: Implement logic to check and avoid duplicate imports.
- No rollback allowed: Imported rows should be saved in the database.

## Deliverables

The deliverables should be ready-to-work, including:

- **Source Code:** All relevant source code for the application.
- **Maven POM File:** The `pom.xml` file with dependencies and configuration.
- **Database Configuration:** Update the `application.properties` file with the correct database configurations.
- **Build Information:** Include build information accessible via http(s)://<hostname>:<port>/actuator/info.

Feel free to reach out if you have any questions or need further assistance. Happy coding!
