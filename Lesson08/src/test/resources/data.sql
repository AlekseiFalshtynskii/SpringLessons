INSERT INTO genre (id, name) VALUES (1, 'Фантастика');
INSERT INTO genre (id, name) VALUES (2, 'Триллер');

INSERT INTO author (id, lastname, firstname) VALUES (1, 'Имя1', 'Фамилия1');
INSERT INTO author (id, lastname, firstname) VALUES (2, 'Имя2', 'Фамилия2');
INSERT INTO author (id, lastname, firstname) VALUES (3, 'Имя3', 'Фамилия3');

INSERT INTO book (id, name, description) VALUES (1, 'Книга1', 'Книга1');
INSERT INTO book (id, name, description) VALUES (2, 'Книга2', 'Книга2');

INSERT INTO comment (id, message, book_id) VALUES (1, 'Коммент1 книги1', 1);
INSERT INTO comment (id, message, book_id) VALUES (2, 'Коммент2 книги1', 1);
INSERT INTO comment (id, message, book_id) VALUES (3, 'Коммент1 книги2', 2);

INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO book_author (book_id, author_id) VALUES (1, 2);
INSERT INTO book_author (book_id, author_id) VALUES (1, 3);
INSERT INTO book_author (book_id, author_id) VALUES (2, 1);

INSERT INTO book_genre (book_id, genre_id) VALUES (1, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (1, 2);
INSERT INTO book_genre (book_id, genre_id) VALUES (2, 2);
