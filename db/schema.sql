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

CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE items_categories
(
    item_id       INTEGER NOT NULL REFERENCES items (id),
    categories_id INTEGER NOT NULL REFERENCES categories (id)
);

INSERT INTO categories (name) VALUES ('Общее');
INSERT INTO categories (name) VALUES ('Предупреждение');
INSERT INTO categories (name) VALUES ('Ошибка');
INSERT INTO categories (name) VALUES ('Пофиксить');
