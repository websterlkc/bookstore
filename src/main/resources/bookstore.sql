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

CREATE TABLE IF NOT EXISTS BOOKSTORE.User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    encrypted_password VARCHAR(255) NOT NULL,
    user_role VARCHAR(50) NOT NULL
);



-- Insert 10 records into the Book table
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780141185064', 'The Grapes of Wrath', 'Fiction', 1939, 10.99);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780451524935', '1984', 'Dystopian', 1949, 9.99);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780747532743', 'Harry Potter and the Philosopher\'s Stone', 'Fantasy', 1997, 12.99);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780684801223', 'The Old Man and the Sea', 'Literary Fiction', 1952, 8.99);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780141439648', 'The Adventures of Huckleberry Finn', 'Adventure', 1884, 7.99);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780743273565', 'The Great Gatsby', 'Literary Fiction', 1925, 10.49);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780062073488', 'Murder on the Orient Express', 'Mystery', 1934, 11.49);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780061120084', 'To Kill a Mockingbird', 'Southern Gothic', 1960, 8.99);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9780307388629', 'Beloved', 'Historical Fiction', 1987, 12.49);
INSERT INTO BOOKSTORE.Book (isbn, title, genre, year, price) VALUES ('9781400079988', 'War and Peace', 'Historical Fiction', 1869, 14.99);


-- Insert 20 records into the Author table
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('John Steinbeck', '1902-02-27', '9780141185064');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Ed Ricketts', '1897-05-14', '9780141185064');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('George Orwell', '1903-06-25', '9780451524935');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('H.G. Wells', '1866-09-21', '9780451524935');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('J.K. Rowling', '1965-07-31', '9780747532743');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('John Tiffany', '1971-12-10', '9780747532743');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Ernest Hemingway', '1899-07-21', '9780684801223');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Pauline Pfeiffer', '1895-07-22', '9780684801223');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Mark Twain', '1835-11-30', '9780141439648');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Charles Dudley Warner', '1829-09-12', '9780141439648');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('F. Scott Fitzgerald', '1896-09-24', '9780743273565');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Zelda Fitzgerald', '1900-07-24', '9780743273565');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Agatha Christie', '1890-09-15', '9780062073488');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Max Mallowan', '1904-05-06', '9780062073488');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Harper Lee', '1926-04-28', '9780061120084');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Truman Capote', '1924-09-30', '9780061120084');  

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Toni Morrison', '1931-02-18', '9780307388629');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Angela Davis', '1944-01-26', '9780307388629'); 

INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Leo Tolstoy', '1828-09-09', '9781400079988');
INSERT INTO BOOKSTORE.Author (name, birthday, isbn) VALUES ('Sophia Tolstaya', '1844-08-22', '9781400079988');  



INSERT INTO BOOKSTORE.User (username, encrypted_password, user_role) VALUES ('adminUser', 'hashed_admin_password', 'admin');
INSERT INTO BOOKSTORE.User (username, encrypted_password, user_role) VALUES ('supportUser', 'hashed_support_password', 'support');
INSERT INTO BOOKSTORE.User (username, encrypted_password, user_role) VALUES ('clientUser', 'hashed_client_password', 'client');
