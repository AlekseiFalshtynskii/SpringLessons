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

INSERT INTO users (id, username, password, enabled) VALUES (1, 'admin', '1', TRUE);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'creator', '1', TRUE);
INSERT INTO users (id, username, password, enabled) VALUES (3, 'editor', '1', TRUE);
INSERT INTO users (id, username, password, enabled) VALUES (4, 'remover', '1', TRUE);

INSERT INTO authorities (user_id, authorities) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authorities) VALUES (2, 'ROLE_CREATOR');
INSERT INTO authorities (user_id, authorities) VALUES (3, 'ROLE_EDITOR');
INSERT INTO authorities (user_id, authorities) VALUES (4, 'ROLE_REMOVER');

INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, 0, 'ROLE_ADMIN'),
  (2, 0, 'ROLE_CREATOR'),
  (3, 0, 'ROLE_EDITOR'),
  (4, 0, 'ROLE_REMOVER');

INSERT INTO acl_class (id, class) VALUES
  (1, 'ru.spring.model.Genre'),
  (2, 'ru.spring.model.Author'),
  (3, 'ru.spring.model.Book');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
VALUES
  (1, 1, 1, NULL, 1, 0),
  (2, 2, 1, NULL, 1, 0),
  (3, 3, 1, NULL, 1, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
  (1, 1, 1, 1, 4, 1, 1, 1),
  (2, 2, 2, 1, 4, 1, 1, 1),
  (3, 3, 3, 1, 4, 1, 1, 1),
  (4, 1, 4, 2, 2, 1, 1, 1),
  (5, 2, 5, 2, 2, 1, 1, 1),
  (6, 3, 6, 2, 2, 1, 1, 1),
  (7, 1, 7, 3, 1, 1, 1, 1),
  (8, 2, 8, 3, 1, 1, 1, 1),
  (9, 3, 9, 3, 1, 1, 1, 1),
  (10, 1, 10, 4, 3, 1, 1, 1),
  (11, 2, 11, 4, 3, 1, 1, 1),
  (12, 3, 12, 4, 3, 1, 1, 1);


