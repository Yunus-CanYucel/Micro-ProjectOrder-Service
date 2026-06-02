


CREATE TABLE IF NOT EXISTS orders
(
    id            bigserial primary key,
    username      varchar(255) UNIQUE NOT NULL,

);

CREATE TABLE IF NOT EXISTS order_items
(
    id       bigserial primary key,
    order_id bigint NOT NULL,
    item_id  bigint NOT NULL,
    quantity int NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);