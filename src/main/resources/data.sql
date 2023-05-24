CREATE TABLE IF NOT EXISTS "user" (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(60) NOT NULL,
    role VARCHAR(10) NOT NULL
);

MERGE INTO "user"
    (id, name, password, role)
    KEY (id)
VALUES (1, 'admin', '$2a$12$nHT5mlzjQTjMxMNzCkZ5Qu2B0A/BJ86SwQgl55P/JFNLa/gbnvzNC', 'ROLE_ADMIN');