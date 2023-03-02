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


CREATE TABLE IF NOT EXISTS game
(
    id           BIGINT AUTO_INCREMENT,
    name         VARCHAR(255)   NOT NULL,
    release_date DATE           NOT NULL,
    genre        VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    CONSTRAINT `PK_game` PRIMARY KEY (id),
    CONSTRAINT `UQ_game_name` UNIQUE KEY (name)
);

CREATE TABLE IF NOT EXISTS user_game
(
    user_id BIGINT,
    game_id BIGINT,
    CONSTRAINT `PK_user_game` PRIMARY KEY (user_id, game_id),
    CONSTRAINT `FK_user_user_id` FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT `FK_user_game_id` FOREIGN KEY (game_id) REFERENCES game (id)
);

#
## POPULATE SOME DATA
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


INSERT INTO game(id, name, release_date, genre, price)
VALUES (1, 'Counter-Strike: Global Offensive', '2012-08-12', 'Tactical First-Person Shooter', 350.00),
       (2, 'Dota 2', '2013-07-09', 'MOBA', 0.00),
       (3, 'Apex Legends', '2019-02-04', 'Battle Royale', 0.00),
       (4, 'PUBG: BATTLEGROUNDS', '2017-03-23', 'Battle Royale', 720.00),
       (5, 'Hogwarts Legacy', '2023-02-10', 'RPG', 1150.00);

INSERT INTO user_game(user_id, game_id)
VALUES (2, 5), (4, 1);

