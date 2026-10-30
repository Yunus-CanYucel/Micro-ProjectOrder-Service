# Micro-Project Order Service

The order service is part of the webshop backend that creates orders, stores orders, 
communicates with product service for what products has been bought
and send order confirmation to show what products has been brought via the Mail Service


## Contents

* [Responsibilities](#responsibilities)



# Responsibilities

The Order Service is responsibile for the following=

* Creating Order A logged in user can create an order containing one or more products
* Checking and decreasing stock. Before an order is saved, the service calls the Product Service to decrease the stock for the requested products.
