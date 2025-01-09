CREATE DATABASE demo;
USE demo;

DROP DATABASE IF EXISTS demo;

CREATE DATABASE demo;
USE demo;

CREATE TABLE User (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      email VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(50) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Quiz (
                      quiz_id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(50) NOT NULL,
                      description TEXT,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Question (
                          question_id INT AUTO_INCREMENT PRIMARY KEY,
                          text TEXT NOT NULL,
                          difficulty ENUM('Easy', 'Medium', 'Hard'),
                          quiz_id INT,
                          FOREIGN KEY (quiz_id) REFERENCES Quiz(quiz_id) ON DELETE CASCADE
);

CREATE TABLE Answer (
                        answer_id INT AUTO_INCREMENT PRIMARY KEY,
                        answer_text TEXT,
                        is_correct BOOLEAN,
                        question_id INT,
                        FOREIGN KEY (question_id) REFERENCES Question(question_id) ON DELETE CASCADE
);

CREATE TABLE Result (
                        result_id INT AUTO_INCREMENT PRIMARY KEY,
                        score INT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        user_id INT,
                        quiz_id INT,
                        FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
                        FOREIGN KEY (quiz_id) REFERENCES Quiz(quiz_id) ON DELETE CASCADE
);

-- Insert data for User table
INSERT INTO User (username, email, password)
VALUES
    ('coder1', 'coder1@example.com', 'hashed_password1'),
    ('coder2', 'coder2@example.com', 'hashed_password2'),
    ('coder3', 'coder3@example.com', 'hashed_password3'),
    ('coder4', 'coder4@example.com', 'hashed_password4'),
    ('coder5', 'coder5@example.com', 'hashed_password5'),
    ('coder6', 'coder6@example.com', 'hashed_password6');

-- Insert data for Quiz table
INSERT INTO Quiz (title, description)
VALUES
    ('Python Quiz', 'Test your Python programming knowledge'),
    ('JavaScript Quiz', 'Test your JavaScript programming skills'),
    ('Java Quiz', 'Test your Java programming knowledge'),
    ('C++ Quiz', 'Test your C++ programming skills'),
    ('Data Structures Quiz', 'Evaluate your knowledge of data structures'),
    ('Algorithms Quiz', 'Challenge your understanding of algorithms');

-- Insert data for Question table
INSERT INTO Question (quiz_id, text, difficulty)
VALUES
    (1, 'What is the output of the following code:\nprint(2 + 2 * 2)', 'Easy'),
    (1, 'What is the correct syntax for creating a list in Python?', 'Medium'),
    (1, 'Explain the difference between a list and a tuple in Python.', 'Hard'),
    (2, 'How do you declare a variable in JavaScript?', 'Easy'),
    (2, 'What is the purpose of the "this" keyword in JavaScript?', 'Medium'),
    (2, 'Explain the concept of closures in JavaScript.', 'Hard'),
    (3, 'What is the purpose of the "main" method in Java?', 'Easy'),
    (3, 'Explain the concept of inheritance in Java.', 'Medium'),
    (3, 'What is the difference between an interface and an abstract class in Java?', 'Hard'),
    (4, 'What is the output of the following C++ code: std::cout << "Hello, World!";', 'Easy'),
    (4, 'Explain the concept of pointers in C++.', 'Medium'),
    (4, 'What is RAII in C++ and why is it important?', 'Hard'),
    (5, 'What is a binary tree?', 'Easy'),
    (5, 'Describe the differences between a stack and a queue.', 'Medium'),
    (5, 'Explain how a hash table works.', 'Hard'),
    (6, 'What is the time complexity of binary search?', 'Easy'),
    (6, 'Describe the difference between quicksort and mergesort.', 'Medium'),
    (6, 'Explain the concept of dynamic programming.', 'Hard');

-- Insert data for Answer table
INSERT INTO Answer (question_id, answer_text, is_correct)
VALUES
    -- Python Quiz
    (1, '6', TRUE),                           -- Question 1
    (1, '8', FALSE),
    (1, '4', FALSE),
    (2, '[1, 2, 3]', TRUE),                   -- Question 2
    (2, '{1, 2, 3}', FALSE),
    (2, '(1, 2, 3)', FALSE),
    (3, 'Lists are mutable, tuples are immutable.', TRUE), -- Question 3
    (3, 'Lists are immutable, tuples are mutable.', FALSE),
    (3, 'Both lists and tuples are immutable.', FALSE),

    -- JavaScript Quiz
    (4, 'let x = 10;', TRUE),                -- Question 4
    (4, 'variable x = 10;', FALSE),
    (4, 'x = 10;', FALSE),
    (5, '"this" refers to the current object.', TRUE), -- Question 5
    (5, '"this" refers to the global object.', FALSE),
    (5, '"this" refers to the parent object.', FALSE),
    (6, 'Closures allow functions to remember their scope.', TRUE), -- Question 6
    (6, 'Closures define class constructors.', FALSE),
    (6, 'Closures allow private variables.', FALSE),

    -- Java Quiz
    (7, 'To define the entry point of a Java program.', TRUE), -- Question 7
    (7, 'To declare variables globally.', FALSE),
    (7, 'To create new threads.', FALSE),
    (8, 'Inheritance allows one class to acquire fields and methods of another.', TRUE), -- Question 8
    (8, 'Inheritance allows multiple classes to be instantiated simultaneously.', FALSE),
    (8, 'Inheritance allows overriding only private methods.', FALSE),
    (9, 'Abstract classes can have concrete methods, interfaces cannot.', TRUE), -- Question 9
    (9, 'Interfaces can have constructors, but abstract classes cannot.', FALSE),
    (9, 'Abstract classes cannot be extended.', FALSE),

    -- C++ Quiz
    (10, '"Hello, World!"', TRUE),            -- Question 10
    (10, 'Compilation error.', FALSE),
    (10, 'Runtime exception.', FALSE),
    (11, 'Pointers store memory addresses.', TRUE), -- Question 11
    (11, 'Pointers are high-level variables.', FALSE),
    (11, 'Pointers are used to store functions.', FALSE),
    (12, 'RAII manages resource allocation using constructors and destructors.', TRUE), -- Question 12
    (12, 'RAII is a runtime garbage collection mechanism.', FALSE),
    (12, 'RAII is specific to STL containers.', FALSE),

    -- Data Structures Quiz
    (13, 'A binary tree has at most two children per node.', TRUE), -- Question 13
    (13, 'A binary tree always has two children per node.', FALSE),
    (13, 'A binary tree is a list.', FALSE),
    (14, 'A stack is LIFO, a queue is FIFO.', TRUE), -- Question 14
    (14, 'A stack is FIFO, a queue is LIFO.', FALSE),
    (14, 'Both stack and queue are unordered.', FALSE),
    (15, 'A hash table stores key-value pairs.', TRUE), -- Question 15
    (15, 'A hash table uses binary search.', FALSE),
    (15, 'A hash table requires sorted data.', FALSE),

    -- Algorithms Quiz
    (16, 'O(log n)', TRUE),                   -- Question 16
    (16, 'O(n)', FALSE),
    (16, 'O(n^2)', FALSE),
    (17, 'Quicksort is usually faster than mergesort.', TRUE), -- Question 17
    (17, 'Quicksort is slower for small arrays.', FALSE),
    (17, 'Mergesort is faster for all inputs.', FALSE),
    (18, 'Dynamic programming solves problems by overlapping subproblems.', TRUE), -- Question 18
    (18, 'Dynamic programming uses recursion exclusively.', FALSE),
    (18, 'Dynamic programming avoids using memory.', FALSE);

-- Insert data for Result table
INSERT INTO Result (user_id, quiz_id, score)
VALUES
    (1, 1, 80),
    (2, 2, 75),
    (3, 3, 90),
    (1, 2, 85),
    (2, 3, 95),
    (3, 1, 70),
    (4, 4, 60),
    (5, 5, 88),
    (6, 6, 77),
    (1, 3, 90),
    (2, 4, 65),
    (3, 5, 80),
    (4, 6, 75),
    (5, 1, 85),
    (6, 2, 78);
