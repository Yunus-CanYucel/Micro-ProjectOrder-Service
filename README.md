# Micro-Project Order Service

The order service is part of the webshop backend that creates orders, stores orders, 
communicates with product service for what products has been bought
and send order confirmation to show what products has been brought via the Mail Service

## Contents

* [Responsibilities](#responsibilities)
* [System Overview](#system-overview)


# Responsibilities

The Order Service is responsibile for the following=

1. Creating Order A logged in user can create an order containing one or more products
2. Checking and decreasing stock. Before an order is saved, the service calls the Product Service to decrease the stock for the requested products.
3. Storing orders. Orders and their order items are stored in the database.
4. Calculating the total price. The total price is calculated from the prices and quantities returned by the Product Service.
5. Retrieving a user's orders. A logged-in user can retrieve orders connected to their JWT identity.
6. Sending order confirmations. After an order is created, a message is sent through RabbitMQ so the Mail Service can send an order confirmation.

It does not manage products or stock directly. That belongs to the Product Service. It also does not handle login or issue JWTs. That belongs to the Auth Service.

# System Overview

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






