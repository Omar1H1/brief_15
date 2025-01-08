CREATE SEQUENCE IF NOT EXISTS student_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS book_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE student (
    id BIGINT NOT NULL DEFAULT nextval('student_seq'),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE book (
    id BIGINT NOT NULL DEFAULT nextval('book_seq'),
    title VARCHAR(255),
    is_available BOOLEAN NOT NULL DEFAULT true,
    student_id BIGINT,
    CONSTRAINT pk_book PRIMARY KEY (id)
);

CREATE TABLE book_genres (
    book_id BIGINT NOT NULL,
    genres VARCHAR(255),
    CONSTRAINT fk_book_genres_on_book FOREIGN KEY (book_id) REFERENCES book (id)
);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_STUDENT FOREIGN KEY (student_id) REFERENCES student (id);