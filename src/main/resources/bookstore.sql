-- Create the BOOKSTORE schema
CREATE SCHEMA IF NOT EXISTS BOOKSTORE;


-- Create the Author table with AUTO_INCREMENT
CREATE TABLE IF NOT EXISTS BOOKSTORE.Author (
    author_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    birthday DATE,
    isbn VARCHAR(13)
);

-- Create the Book table with AUTO_INCREMENT
CREATE TABLE IF NOT EXISTS BOOKSTORE.Book (
    bid BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(13),
    title VARCHAR(255),
    genre VARCHAR(50),
    year INT,
    price DECIMAL(10, 2)
);


CREATE TABLE BOOKSTORE.book_author (
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES BOOKSTORE.Book(bid) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES BOOKSTORE.Author(author_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS BOOKSTORE.User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    encrypted_password VARCHAR(255) NOT NULL,
    user_role VARCHAR(50) NOT NULL
);



-- Insert 10 records into the Book table
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (1, '9780141185064', 'The Grapes of Wrath', 'Fiction', 1939, 10.99);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (2, '9780451524935', '1984', 'Dystopian', 1949, 9.99);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (3, '9780747532743', 'Harry Potter and the Philosopher\'s Stone', 'Fantasy', 1997, 12.99);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (4, '9780684801223', 'The Old Man and the Sea', 'Literary Fiction', 1952, 8.99);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (5, '9780141439648', 'The Adventures of Huckleberry Finn', 'Adventure', 1884, 7.99);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (6, '9780743273565', 'The Great Gatsby', 'Literary Fiction', 1925, 10.49);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (7, '9780062073488', 'Murder on the Orient Express', 'Mystery', 1934, 11.49);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (8, '9780061120084', 'To Kill a Mockingbird', 'Southern Gothic', 1960, 8.99);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (9, '9780307388629', 'Beloved', 'Historical Fiction', 1987, 12.49);
INSERT INTO BOOKSTORE.Book (bid, isbn, title, genre, year, price) VALUES (10, '9781400079988', 'War and Peace', 'Historical Fiction', 1869, 14.99);


-- Insert 20 records into the Author table
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (1, 'John Steinbeck', '1902-02-27', '9780141185064');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (2, 'Ed Ricketts', '1897-05-14', '9780141185064');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (3, 'George Orwell', '1903-06-25', '9780451524935');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (4, 'H.G. Wells', '1866-09-21', '9780451524935');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (5, 'J.K. Rowling', '1965-07-31', '9780747532743');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (6, 'John Tiffany', '1971-12-10', '9780747532743');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (7, 'Ernest Hemingway', '1899-07-21', '9780684801223');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (8, 'Pauline Pfeiffer', '1895-07-22', '9780684801223');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (9, 'Mark Twain', '1835-11-30', '9780141439648');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (10, 'Charles Dudley Warner', '1829-09-12', '9780141439648');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (11, 'F. Scott Fitzgerald', '1896-09-24', '9780743273565');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (12, 'Zelda Fitzgerald', '1900-07-24', '9780743273565');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (13, 'Agatha Christie', '1890-09-15', '9780062073488');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (14, 'Max Mallowan', '1904-05-06', '9780062073488');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (15, 'Harper Lee', '1926-04-28', '9780061120084');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (16, 'Truman Capote', '1924-09-30', '9780061120084');  

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (17, 'Toni Morrison', '1931-02-18', '9780307388629');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (18, 'Angela Davis', '1944-01-26', '9780307388629'); 

INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (19, 'Leo Tolstoy', '1828-09-09', '9781400079988');
INSERT INTO BOOKSTORE.Author (author_id, name, birthday, isbn) VALUES (20, 'Sophia Tolstaya', '1844-08-22', '9781400079988');  



INSERT INTO BOOKSTORE.User (username, encrypted_password, user_role) VALUES ('adminUser', 'hashed_admin_password', 'admin');
INSERT INTO BOOKSTORE.User (username, encrypted_password, user_role) VALUES ('supportUser', 'hashed_support_password', 'support');
INSERT INTO BOOKSTORE.User (username, encrypted_password, user_role) VALUES ('clientUser', 'hashed_client_password', 'client');




To insert multiple records into the book_author table with book_id ranging from 1 to 10 and author_id ranging from 1 to 20, you can use a nested loop in SQL. Below are the SQL insert statements that will cover all combinations of books and authors for the specified ranges:

SQL Insert Statements
sql
Copy code
-- Book ID 1
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (1, 2);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (2, 3);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (2, 4);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (3, 5);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (3, 6);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (4, 7);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (4, 8);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (5, 9);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (5, 10);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (6, 11);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (6, 12);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (7, 13);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (7, 14);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (8, 15);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (8, 16);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (9, 17);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (9, 18);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (10, 19);
INSERT INTO BOOKSTORE.book_author (book_id, author_id) VALUES (10, 20);
