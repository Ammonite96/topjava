DROP TABLE IF EXISTS meals;

CREATE TABLE meals
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    datetime TIME NOT NULL,
    description VARCHAR NOT NULL,
    calories INTEGER NOT NULL,
    CONSTRAINT datetime_idx UNIQUE (datetime),
    FOREIGN KEY (id) REFERENCES users(id)
);



