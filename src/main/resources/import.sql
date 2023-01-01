INSERT INTO users (id, user_name, first_name, last_name, password, email) VALUES (1,'admin','John','Smith','$2a$08$o02ZXB25oJiMCsqsRkdV6ukHUhz1DrXATIPOPqjuOwF8Ywy6OxYRq','admin@mail.ru');

INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);

# admin password ---> admin
