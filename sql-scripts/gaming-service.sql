CREATE SCHEMA IF NOT EXISTS gaming_service;
USE gaming_service;

CREATE TABLE IF NOT EXISTS user
(
    id         BIGINT AUTO_INCREMENT,
    first_name VARCHAR(255)   NOT NULL,
    last_name  VARCHAR(255)   NOT NULL,
    email      VARCHAR(255)   NOT NULL,
    balance    DECIMAL(10, 2) NOT NULL,
    gender     VARCHAR(255)   NOT NULL,
    created_at DATETIME DEFAULT now(),
    CONSTRAINT `PK_user` PRIMARY KEY (id),
    CONSTRAINT `UQ_user_email` UNIQUE KEY (email)
);

CREATE TABLE IF NOT EXISTS user_profile
(
    user_id BIGINT AUTO_INCREMENT,
    country VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    street  VARCHAR(255) NOT NULL,
    CONSTRAINT `PK_user_profile` PRIMARY KEY (user_id),
    CONSTRAINT `FK_user_user_profile` FOREIGN KEY (user_id) REFERENCES user (id)
);

#
# populate some data
#

INSERT INTO user (id, first_name, last_name, email, balance, gender, created_at)
VALUES (1, 'Rick', 'Novak', 'rick.novak@gmail.com', 0.00, 'MALE', now()),
       (2, 'Olivia', 'Clark', 'olivia.clark@gmail.com', 500.00, 'FEMALE', now()),
       (3, 'Noah', 'Smith', 'noah.smith@gmail.com', 0.00, 'MALE', now()),
       (4, 'Charlotte', 'Williams', 'charlotte.williams@gmail.com', 2300.25, 'FEMALE', now());

INSERT INTO user_profile (user_id, country, city, street)
VALUES (1, 'Canada', 'Toronto', 'Milmink Street'),
       (2, 'Australia', 'Perth', 'Brisbane Terrace'),
       (3, 'Ireland', 'Dublin', 'Henrietta Street'),
       (4, 'Italy', 'Milan', 'Via Dante');

