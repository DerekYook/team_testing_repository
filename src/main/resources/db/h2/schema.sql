CREATE TABLE IF NOT EXISTS TODOS (
    id int NOT NULL AUTO_INCREMENT,
    title varchar(200) NOT NULL UNIQUE,
    todo_order int NOT NULL,
    completed boolean NOT NULL,
    PRIMARY KEY (id)
);