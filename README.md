# Medicine Inventory System - Microservices Architecture

This repository contains a Spring Cloud microservices architecture for a Medicine Inventory Management System.

## Architecture Components

1. **Eureka Server (Discovery Service)**
   - Port: 8761
   - URL: http://localhost:8761
   - Purpose: Service registry for microservices discovery

2. **Config Server**
   - Port: 8888
   - URL: http://localhost:8888
   - Purpose: Centralized configuration management

3. **Medicine Inventory Service**
   - Port: 8080 (default)
   - URL: http://localhost:8080
   - Purpose: Core business logic for medicine inventory management

## Getting Started

### Prerequisites
- Java 17
- Maven 3.6+
- Git

### Running the Services

1. **Start the Eureka Server**
   ```bash
   cd med-system/eureka-server
   mvn spring-boot:run
   ```

2. **Start the Config Server**
   ```bash
   cd med-system/config-server
   mvn spring-boot:run
   ```

3. **Start the Medicine Inventory Service**
   ```bash
   cd med-system/med-inventory-service
   mvn spring-boot:run
   ```

4. **Start additional instances of the Medicine Inventory Service (for load balancing)**
   ```bash
   cd med-system/med-inventory-service
   mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
   ```

## Load Balancing

The system uses Spring Cloud Load Balancer to distribute requests across multiple instances of the Medicine Inventory Service. When multiple instances are running, the load balancer will automatically distribute traffic between them.

## Configuration

The Config Server fetches configuration from this GitHub repository. Configuration files are located in the `med-system-config/config-files` directory.

## Monitoring

- Eureka Dashboard: http://localhost:8761
- Actuator Endpoints: http://localhost:8080/actuator

## Features

- RESTful API endpoints for medicine inventory management
- In-memory H2 database
- Spring Data JPA for data persistence
- Spring Actuator for monitoring and metrics
- Lombok for reducing boilerplate code

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── medsystem/
│   │           └── inventory/
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── medsystem/
                └── inventory/
``` 