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

CREATE TABLE IF NOT EXISTS feedback
(
    id      BIGINT AUTO_INCREMENT,
    message VARCHAR(255) NOT NULL,
    rating  INT          NOT NULL,
    game_id BIGINT       NOT NULL,
    user_id BIGINT       NOT NULL,
    CONSTRAINT `PK_feedback` PRIMARY KEY (id),
    CONSTRAINT `CHK_feedback_rating` CHECK (rating >= 0 AND rating <= 5),
    CONSTRAINT `FK_feedback_user_id` FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT `FK_feedback_game_id` FOREIGN KEY (game_id) REFERENCES game (id)
);


#
## POPULATE SOME DATA
#

INSERT INTO user (id, first_name, last_name, email, balance, gender, created_at)
VALUES (1, 'Rick', 'Novak', 'rick.novak@yahoo.com', 0.00, 'MALE', now()),
       (2, 'Olivia', 'Clark', 'olivia.clark@gmail.com', 500.00, 'FEMALE', now()),
       (3, 'Noah', 'Smith', 'noah.smith@hotmail.com', 0.00, 'MALE', now()),
       (4, 'Charlotte', 'Williams', 'charlotte.williams@gmail.com', 2300.25, 'FEMALE', now()),
       (5, 'Bob', 'Jhonson', 'bob.jhonson@gmail.com', 52.64, 'MALE', now()),
       (6, 'Sophia', 'Lopez', 'sophia.lopez@outlook.com', 100.50, 'FEMALE', now()),
       (7, 'Lucas', 'Moore', 'lucas.moore@outlook.com', 23250.00, 'MALE', now()),
       (8, 'James', 'Wilson', 'james.wilson@outlook.com', 13400.15, 'MALE', now()),
       (9, 'Henry', 'King', 'henry.king@outlook.com', 1700.00, 'MALE', now()),
       (10, 'Lucas', 'Novak', 'lucas.novak@yahoo.com', 3400.60, 'MALE', now()),
       (11, 'Bob', 'Lopez', 'bob.lopez@iCloud.com', 0.00, 'MALE', now()),
       (12, 'James', 'Williams', 'james.williams@iCloud.com', 4200.13, 'MALE', now()),
       (13, 'Rick', 'Clark', 'rick.clark@iCloud.com', 75.00, 'MALE', now()),
       (14, 'Sophia', 'Moore', 'sophia.moore@iCloud.com', 3125.50, 'FEMALE', now()),
       (15, 'Noah', 'Williams', 'noah.williams@iCloud.com', 2100.00, 'MALE', now()),
       (16, 'Zakhar', 'Pavlenko', 'zakhar.pavlenko@yandex.ua', 1255.00, 'MALE', now()),
       (17, 'Yevheniia', 'Boyko', 'yevheniia.boyko@gmail.com', 0.00, 'FEMALE', now()),
       (18, 'Zhanna', 'Tkachenko', 'zhanna.tkachenko@yandex.ua', 5892.50, 'FEMALE', now()),
       (19, 'Dmitry ', 'Kovalchuk', 'dmitry.kovalchuk@gmail.com', 12750.00, 'MALE', now()),
       (20, 'Halyna', 'Havrylyuk', 'halyna.havrylyuk@yandex.ua', 7570.55, 'FEMALE', now());


INSERT INTO user_profile (user_id, country, city, street)
VALUES (1, 'Canada', 'Toronto', 'Milmink Street'),
       (2, 'Australia', 'Perth', 'Brisbane Terrace'),
       (3, 'Ireland', 'Dublin', 'Henrietta Street'),
       (4, 'Italy', 'Milan', 'Via Dante'),
       (5, 'Norway', 'Stavanger', 'Baneveien'),
       (6, 'Finland', 'Helsinki', 'Esplanadi'),
       (7, 'Sweden', 'Stockholm', 'Svartensgatan'),
       (8, 'Iceland', 'Akureyri', 'Valagil'),
       (9, 'Switzerland', 'Bern', 'Blockweg'),
       (10, 'Canada', 'Toronto', 'Milmink Street'),
       (11, 'Australia', 'Sydney', 'King Street'),
       (12, 'Italy', 'Rome', 'Via del Corso'),
       (13, 'Finland', 'Espoo', 'Aamutie'),
       (14, 'Canada', 'Montreal', 'Sainte-Catherine Street'),
       (15, 'Norway', 'Trondheim', 'Bakkegata'),
       (16, 'Ukraine', 'Mykolaiv', 'Kotelna Street'),
       (17, 'Ukraine', 'Odesa', 'Shishkina 1 Per'),
       (18, 'Ukraine', 'Lviv', 'Staroyevryeiska Street'),
       (19, 'Ukraine', 'Kiev', 'Ivana Franka Street'),
       (20, 'Ukraine', 'Vinnytsia', 'Sverdlova street');


INSERT INTO game(id, name, release_date, genre, price)
VALUES (1, 'Counter-Strike: Global Offensive', '2012-08-12', 'Shooter', 350.00),
       (2, 'Dota 2', '2013-07-09', 'MOBA', 0.00),
       (3, 'Apex Legends', '2019-02-04', 'Battle Royale', 0.00),
       (4, 'PUBG: BATTLEGROUNDS', '2017-03-23', 'Battle Royale', 720.50),
       (5, 'Grand Theft Auto V', '2013-09-17', 'Action-adventure', 1105.00),
       (6, 'Rust', '2013-12-11', 'Survival', 1475.00),
       (7, 'Sons Of The Forest', '2023-02-23', 'Survival', 1150.25),
       (8, 'Rocket League', '2015-07-07', 'Sport', 0.00),
       (9, 'ELDEN RING', '2022-02-25', 'RPG', 2250.99),
       (10, 'Football Manager 2023', '2022-11-08', 'Sport', 2150.00),
       (11, 'FIFA 23', '2022-09-27', 'Sport', 2585.45),
       (12, 'Left 4 Dead 2', '2009-11-17', 'Shooter', 370.50),
       (13, 'PAYDAY 2', '2013-08-13', 'Shooter', 350.00),
       (14, 'Terraria', '2011-05-16', 'Action-adventure', 335.99),
       (15, 'Crab Game', '2021-10-29', 'Battle Royale', 0.00);

INSERT INTO user_game(user_id, game_id)
VALUES (1, 2),
       (1, 3),
       (3, 9),
       (3, 6),
       (3, 7),
       (6, 14),
       (7, 1),
       (7, 15),
       (7, 9),
       (7, 7),
       (7, 6),
       (7, 11),
       (8, 10),
       (8, 11),
       (8, 8),
       (8, 12),
       (12, 15),
       (12, 4),
       (13, 5),
       (13, 10),
       (17, 15),
       (17, 8),
       (17, 10),
       (20, 11),
       (20, 10);

INSERT INTO feedback(id, message, rating, game_id, user_id)
VALUES (1, 'The best game i\'ve ever played', 5, 5, 13),
       (2, 'Spent so much time playing this game and i think it was a waste of time, don\'t recommend playing this game', 1, 2, 1),
       (3, 'it was so difficult to finish this game but eventually i managed to do it', 4, 9, 3),
       (4, 'this game became boring for me after 5 hours of playing', 3, 14, 6),
       (5, 'waste of money, almost the same as the previous version of this game', 3, 7, 3),
       (6, 'i love this game, played a lot of times despite the fact that this game is so old', 5, 12, 8),
       (7, 'bad game, don\'t really like it', 2, 15, 17),
       (8, 'a good game to play with your friends in squads', 5, 4, 13),
       (9, 'best battle royale i have ever played', 4, 3, 1),
       (10, 'i\'m always playing this game with my friends, no doubts a really good game', 5, 11, 8);





