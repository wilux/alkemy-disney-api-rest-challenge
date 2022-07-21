-- INSERT INTO user (id, email, password) VALUES (10000,'Horror','2022-07-15');
INSERT INTO gender (id, name, DATE_CREATED, LAST_UPDATED) VALUES (10000,'Horror','2022-07-15','2022-07-15');
INSERT INTO gender (id, name, DATE_CREATED, LAST_UPDATED) VALUES (10001,'Comedy','2022-07-15','2022-07-15');
INSERT INTO gender (id, name, DATE_CREATED, LAST_UPDATED) VALUES (10002, 'Drama','2022-07-15','2022-07-15');
INSERT INTO gender (id, name, DATE_CREATED, LAST_UPDATED) VALUES (10003,'Romance','2022-07-15','2022-07-15');
INSERT INTO movie (id, image, title, DATE_CREATED, LAST_UPDATED,GENDER_ID) VALUES (10000,'ImageMovie1', 'Movie 1', '2022-07-15','2022-07-15',10000);
INSERT INTO movie (id, image, title, DATE_CREATED, LAST_UPDATED,GENDER_ID) VALUES (10001,'ImageMovie2', 'Movie 2', '2022-07-16','2022-07-16',10001);
INSERT INTO movie (id, image, title, DATE_CREATED, LAST_UPDATED,GENDER_ID) VALUES (10002,'ImageMovie3', 'Movie 3', '2022-07-17','2022-07-17',10002);
INSERT INTO movie (id, image, title, DATE_CREATED, LAST_UPDATED,GENDER_ID) VALUES (10003,'ImageMovie4', 'Movie 4', '2022-07-18','2022-07-18',10003);
INSERT INTO character (id, image, name, age, weight, history, DATE_CREATED, LAST_UPDATED, MOVIE_ID) VALUES (10000,'ImageCharacter1', 'Name1','24','70', 'History A', '2022-07-15','2022-07-15',10000);
INSERT INTO character (id, image, name, age, weight, history, DATE_CREATED, LAST_UPDATED, MOVIE_ID) VALUES (10001,'ImageCharacterb', 'Nameb','24','70', 'History A1', '2022-07-15','2022-07-15',10000);
INSERT INTO character (id, image, name, age, weight, history, DATE_CREATED, LAST_UPDATED, MOVIE_ID) VALUES (10002,'ImageCharacterc', 'Namec','24','70', 'History A2', '2022-07-15','2022-07-15',10000);
INSERT INTO character (id, image, name, age, weight, history, DATE_CREATED, LAST_UPDATED, MOVIE_ID) VALUES (10003,'ImageCharacter2', 'Name2','24','70', 'History B', '2022-07-15','2022-07-15',10001);
INSERT INTO character (id, image, name, age, weight, history, DATE_CREATED, LAST_UPDATED, MOVIE_ID) VALUES (10004,'ImageCharacter3', 'Name3','24','70', 'History C', '2022-07-15','2022-07-15',10002);
INSERT INTO character (id, image, name, age, weight, history, DATE_CREATED, LAST_UPDATED, MOVIE_ID) VALUES (10005,'ImageCharacter4', 'Name4','24','70', 'History D', '2022-07-15','2022-07-15',10003);
