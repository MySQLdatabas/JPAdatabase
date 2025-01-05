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
    (1, '6', TRUE),
    (1, '8', FALSE),
    (1, '4', FALSE),
    (2, 'var myVar = "value";', TRUE),
    (2, 'let myVar = "value";', TRUE),
    (2, 'const myVar = "value";', TRUE),
    (2, 'myVar = "value";', FALSE),
    (3, 'It refers to the current object.', TRUE),
    (3, 'It is used to create a new object.', FALSE),
    (3, 'It is a reserved keyword.', FALSE),
    (4, 'Closures allow functions to access and "remember" variables from the scope in which they were created.', TRUE),
    (4, 'Closures are used to define anonymous functions.', FALSE),
    (4, 'Closures are only available in modern JavaScript versions.', FALSE),
    (5, 'It is the entry point of a Java program.', TRUE),
    (5, 'It is used to define the main class.', FALSE),
    (5, 'It is optional in all Java programs.', FALSE),
    (6, 'Inheritance allows classes to inherit properties and methods from other classes.', TRUE),
    (6, 'Inheritance is only used for interface implementation.', FALSE),
    (6, 'Inheritance is not supported in Java.', FALSE),
    (7, 'An interface defines a contract of methods that a class must implement, while an abstract class can have both abstract and concrete methods.', TRUE),
    (7, 'Interfaces can have constructors, while abstract classes cannot.', FALSE),
    (7, 'There is no difference between interfaces and abstract classes.', FALSE),
    (8, 'Hello, World!', TRUE),
    (8, 'hello world', FALSE),
    (8, 'Hello World', FALSE),
    (9, 'Pointers are variables that store the memory address of another variable.', TRUE),
    (9, 'Pointers are used to store data in C++.', FALSE),
    (9, 'Pointers are functions in C++.', FALSE),
    (10, 'RAII stands for Resource Acquisition Is Initialization, and it is a programming idiom that ensures resource management.', TRUE),
    (10, 'RAII is a memory management model in Java.', FALSE),
    (10, 'RAII is not used in C++.', FALSE),
    (11, 'A binary tree is a tree data structure in which each node has at most two children.', TRUE),
    (11, 'A binary tree is a graph.', FALSE),
    (11, 'A binary tree can have only one child per node.', FALSE),
    (12, 'A stack is a Last-In-First-Out (LIFO) data structure, while a queue is First-In-First-Out (FIFO).', TRUE),
    (12, 'Both stack and queue are LIFO.', FALSE),
    (12, 'Both stack and queue are FIFO.', FALSE),
    (13, 'A hash table is a data structure that maps keys to values for efficient lookup.', TRUE),
    (13, 'A hash table is a tree structure.', FALSE),
    (13, 'A hash table is used for sorting data.', FALSE),
    (14, 'The time complexity of binary search is O(log n).', TRUE),
    (14, 'The time complexity of binary search is O(n).', FALSE),
    (14, 'The time complexity of binary search is O(1).', FALSE),
    (15, 'Quicksort is generally faster for smaller datasets and in-place, while mergesort is more stable and better for larger datasets.', TRUE),
    (15, 'Quicksort is always faster than mergesort.', FALSE),
    (15, 'Mergesort is always faster than quicksort.', FALSE),
    (16, 'Dynamic programming is a method for solving complex problems by breaking them down into simpler subproblems.', TRUE),
    (16, 'Dynamic programming is a type of sorting algorithm.', FALSE),
    (16, 'Dynamic programming is a data structure.', FALSE);

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
