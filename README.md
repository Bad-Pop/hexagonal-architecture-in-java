# Hexagonal Architecture Demonstration

## Presentation

This very simple project was developed for a technical presentation at Carbon IT. Functionally, it manages driving
licences, in a simple way, saved in a postgre relational database, and exposes some endpoints. These REST endpoints
allow us to manage driving licences.

## Technical stack

- Java 17
- Spring boot 2.4.11
- Vavr
- Lombok
- Spring data jpa
- PostgreSQL
- Junit 5
- Mockito
- AssertJ

## Application breakdown

### The domain module

This is the most important module of the application. It contains all the business logic. In our case, the business
logic means : driving licences management.

### The client module

This module contains one or more sub-modules, designed to respond to interactions with users. In our case, only one
module was created to manage the REST part. However, we could imagine creating another one to consume a Kafka topic for
example.

#### The rest-adapter module

This module contains all the technical code for exposing and interacting with REST resources.

### The server module

This module contains one or more sub-modules for interacting with all the external services necessary for the operation
of the application. In our case we have only one. But we could imagine other modules. For example, a module to connect
to a flipping feature system.

#### The postgres-adapter module

This module contains all the technical code to interact with the postgres database.