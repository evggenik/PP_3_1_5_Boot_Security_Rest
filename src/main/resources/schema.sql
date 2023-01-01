-- начальная таблица
# create table userEntity
# (
#     id        int auto_increment
#         primary key,
#     user_name varchar(45)          not null,
#     password  varchar(255)         not null,
#     email     varchar(45)          not null,
#     roles     varchar(45)          not null,
#     active    tinyint(1) default 1 null
# );

-- The standard JDBC implementation of the UserDetailsService (JdbcDaoImpl) 
#  create table users(
#  	username varchar_ignorecase(50) not null primary key,
# 	password varchar_ignorecase(50) not null,
# 	enabled boolean not null
# );
#
# create table authorities (
# 	username varchar_ignorecase(50) not null,
# 	authority varchar_ignorecase(50) not null,
# 	constraint fk_authorities_users foreign key(username) references users(username)
# );
# create unique index ix_auth_username on authorities (username,authority);


