CREATE TABLE IF NOT EXISTS author (
  id        SERIAL PRIMARY KEY,
  lastname  TEXT NOT NULL,
  firstname TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS genre (
  id   SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS book (
  id          SERIAL PRIMARY KEY,
  name        TEXT NOT NULL,
  description TEXT NULL
);

CREATE TABLE IF NOT EXISTS comment (
  id      SERIAL PRIMARY KEY,
  message TEXT NOT NULL,
  book_id INTEGER REFERENCES book (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS book_author (
  book_id   INTEGER REFERENCES book (id) ON UPDATE CASCADE,
  author_id INTEGER REFERENCES author (id) ON UPDATE CASCADE,
  CONSTRAINT book_author_id PRIMARY KEY (book_id, author_id)
);

CREATE TABLE IF NOT EXISTS book_genre (
  book_id  INTEGER REFERENCES book (id) ON UPDATE CASCADE,
  genre_id INTEGER REFERENCES genre (id) ON UPDATE CASCADE,
  CONSTRAINT book_genre_id PRIMARY KEY (book_id, genre_id)
);
