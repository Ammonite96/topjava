DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2021-02-20 10:00:00', 'завтрак', 300, 100000),
       ('2021-02-20 13:00:00', 'обед', 1500, 100000),
       ('2021-02-20 20:00:00', 'ужин', 100, 100000),
       ('2021-02-21 10:00:00', 'завтрак', 600, 100000),
       ('2021-02-21 13:00:00', 'обед', 800, 100000),
       ('2021-02-21 20:00:00', 'ужин', 400, 100000),
       ('2021-02-22 14:00:00', 'admin обед', 510, 100001),
       ('2021-02-22 21:00:00', 'admin ужин', 1500, 100001);