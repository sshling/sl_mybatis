CREATE TABLE demo_user
(
    id long PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    age int,
    createDate datetime,
    updateDate timestamp
);
CREATE UNIQUE INDEX demo_user_name_uindex ON demo_user (name);