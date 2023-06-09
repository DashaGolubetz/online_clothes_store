CREATE TABLE IF NOT EXISTS "user" (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS "product" (
    id IDENTITY PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(1023) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS "cart" (
    user_id INTEGER NOT NULL REFERENCES "user" (id),
    product_id INTEGER NOT NULL REFERENCES "product" (id),
    PRIMARY KEY (user_id, product_id)
);

MERGE INTO "user"
    (name, password, email, role)
    KEY (name)
VALUES ('admin', '$2a$12$nHT5mlzjQTjMxMNzCkZ5Qu2B0A/BJ86SwQgl55P/JFNLa/gbnvzNC', 'example@email.com', 'ROLE_ADMIN');

MERGE INTO "product"
    (title, description, price)
    KEY (title)
VALUES
    ('Телефон', 'Удобное устройство для связи', 999.99),
    ('Телевизор', 'Цифровой источник информации', 9999.99),
    ('Компьютер', 'Технологичное устройство для работы', 99999.99);