### Project Dependencies

The **Patient Service** module includes the following dependencies:

1. **Spring Web** – for building RESTful web services.
2. **Validation** – for handling request data validation using annotations.
3. **Spring Data JPA** – for database interaction and ORM support.
4. **PostgreSQL Driver** – for connecting the application to a PostgreSQL database.
5. **Lombok** – to reduce boilerplate code such as getters, setters, and constructors.

---

### Swagger Documentation Setup

To generate API documentation, the **Springdoc OpenAPI** library was added to the project using the following Maven dependency:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.13</version>
</dependency>
```

After updating the controller, the Swagger specification can be accessed at:

```
http://localhost:8080/v3/api-docs
```

This endpoint returns the API documentation in JSON format.
Copy the JSON output and paste it into the **[Swagger Editor](https://editor.swagger.io/)** to visualize and test the API endpoints.

---
