INSERT INTO genre (id, name) VALUES (1, 'Программирование');

INSERT INTO author (id, lastname, firstname) VALUES (1, 'Уоллс', 'Крейг');
INSERT INTO author (id, lastname, firstname) VALUES (2, 'Жемеров', 'Дмитрий');
INSERT INTO author (id, lastname, firstname) VALUES (3, 'Исакова', 'Светлана');

INSERT INTO book (id, name, description) VALUES (1, 'Spring в действии', '');
INSERT INTO book (id, name, description) VALUES (2, 'Kotlin в действии', '');

INSERT INTO comment (id, message, book_id) VALUES (1, 'Отличная книга', 1);
INSERT INTO comment (id, message, book_id) VALUES (2, 'Нормальная книга', 1);
INSERT INTO comment (id, message, book_id) VALUES (3, 'Не очень книга', 2);

INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO book_author (book_id, author_id) VALUES (2, 2);
INSERT INTO book_author (book_id, author_id) VALUES (2, 3);

INSERT INTO book_genre (book_id, genre_id) VALUES (1, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (2, 1);
