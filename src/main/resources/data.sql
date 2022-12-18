INSERT INTO user (user_name, password, email, roles, active)
values ('user',
        'user',
        'user@mail.ru',
        'ROLE_USER'
        1);

INSERT INTO user (username, password, email, roles, active)
values ('admin',
        'admin',
        'admin@mail.ru',
        'ROLE_ADMIN'
        1);

-- INSERT INTO authorities (username, authority)
-- values ('user', 'ROLE_USER');
--
-- INSERT INTO authorities (username, authority)
-- values ('admin', 'ROLE_ADMIN');