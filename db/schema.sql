CREATE TABLE items
(
    id          SERIAL PRIMARY KEY,
    description TEXT      NOT NULL,
    created     TIMESTAMP NOT NULL,
    done        BOOLEAN   NOT NULL,
    user_id     INT       NOT NULL REFERENCES users (id)
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);