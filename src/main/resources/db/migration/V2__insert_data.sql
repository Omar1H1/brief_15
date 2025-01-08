CREATE TABLE borrowing_history (
    book_id BIGINT PRIMARY KEY,
    borrow_count INT DEFAULT 0,
    CONSTRAINT fk_borrowing_history_book FOREIGN KEY (book_id) REFERENCES book (id)
);


CREATE OR REPLACE FUNCTION log_borrowing()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.student_id IS NOT NULL THEN
        INSERT INTO borrowing_history (book_id, borrow_count)
        VALUES (NEW.id, 1)
        ON CONFLICT (book_id)
        DO UPDATE SET borrow_count = borrowing_history.borrow_count + 1;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER after_book_borrowed
AFTER UPDATE OF student_id ON book
FOR EACH ROW
WHEN (NEW.student_id IS NOT NULL)
EXECUTE FUNCTION log_borrowing();

INSERT INTO student (first_name, last_name) VALUES
('John', 'Doe'),
('Jane', 'Smith'),
('Alice', 'Johnson'),
('Bob', 'Brown'),
('Charlie', 'Davis'),
('Diana', 'Miller'),
('Ethan', 'Wilson'),
('Fiona', 'Moore'),
('George', 'Taylor'),
('Hannah', 'Anderson');


INSERT INTO book (title, is_available, student_id) VALUES
('The Great Gatsby', false, 1),
('To Kill a Mockingbird', true, NULL),
('1984', false, 2),
('Pride and Prejudice', true, NULL),
('The Catcher in the Rye', true, NULL),
('The Hobbit', false, 3),
('Fahrenheit 451', true, NULL),
('The Lord of the Rings', false, 4),
('Brave New World', true, NULL),
('The Alchemist', false, 5);


INSERT INTO book_genres (book_id, genres) VALUES
(1, 'FICTION'),
(2, 'FICTION'),
(3, 'DYSTOPIAN'),
(4, 'ROMANCE'),
(5, 'FICTION'),
(6, 'FANTASY'),
(7, 'DYSTOPIAN'),
(8, 'FANTASY'),
(9, 'DYSTOPIAN'),
(10, 'FICTION');


