# Micro-Project Order Service

The order service is part of the webshop backend that creates orders, stores orders, 
communicates with product service for what products has been bought
and then creates an order confirmation request to the mail service

## Contents

* [Responsibilities](#responsibilities)
* [System Overview](#system-overview)
* [Technologies](#technologies)
* [Database](#Database)






## Responsibilities

The Order Service is responsibile for the following=

1. Creating Order A logged in user can create an order containing one or more products
2. Checking and decreasing stock. Before an order is saved, the service calls the Product Service to decrease the stock for the requested products.
3. Storing orders. Orders and their order items are stored in the database.
4. Calculating the total price. The total price is calculated from the prices and quantities returned by the Product Service.
5. Retrieving a user's orders. A logged-in user can retrieve orders connected to their JWT identity.
6. Sending order confirmations. After an order is created, a message is sent through RabbitMQ so the Mail Service can send an order confirmation.

It does not manage products or stock directly. That belongs to the Product Service. It also does not handle login or issue JWTs. That belongs to the Auth Service.

## System Overview

```
+----------+       +---------+       +-------------------+
| Frontend | ----> | Gateway | ----> |   Order service   |  :8082
|  :5173   |       |  :8090  |       +---------+---------+
+----------+       +---------+                 |
                                               |
                     +-------------------------+------------------------+
                     |                         |                        |
                     v                         v                        v
             +---------------+         +---------------+        +-------------+
             | Product       |         | Auth service  |        | RabbitMQ    |
             | service :8081 |         |     :8080     |        +------+------+
             +---------------+         +---------------+               |
                                                                        v
                                                                +--------------+
                                                                | Mail service |
                                                                +--------------+
```

The Front end should be calling the backend through gateway not directly

when an order is created it communicates with the product service
to decrease the ordered products

## Technologies

| Area                             | Technology                                       |
|----------------------------------|--------------------------------------------------|
| Language                         | Java 17                                          |
| Framework                        | Spring Boot 4.0.6                                |
| REST API                         | Spring Web                                       |
| Authentication and authorization | Spring Security                                  |
| JWT validation                   | Spring Security OAuth2 Resource Server           |
| Persistence                      | Spring Data JPA                                  |
| Database                         | H2 in memory database                            |
| API documentation                | Springdoc OpenAPI 3.0.2                          |
| Object mapping                   | Manual mapping in service code                   |
| Boilerplate reduction            | Lombok                                           |
| Build tool                       | Maven / Maven Wrapper                            |
| Testing                          | JUnit, Spring Boot Test and Spring Security Test |
| Container                        | Docker                                           |
| CI                               | Github Actions                                   |

## Database
It uses H2 in memory 
spring.datasource.url
look in [application.properties](src/main/resources/application.properties) for exact value

H2 in memory doesnt save orders directly to disc and only stays as long as the service never shuts down. Is done like this so that during making of project doesnt fill up the computer. 

if you want the old orders to stay in between restarts change value of spring.data source.url to
`
jdbc:h2:file:./data/orderdb
`
The database contains 2 main entities
* orders
* order_items



