CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    age INTEGER
);

CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200),
    credits INTEGER
);

CREATE TABLE enrollment (
    id SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES student(id),
    course_id INTEGER REFERENCES course(id),
    grade DOUBLE PRECISION
);

INSERT INTO student (first_name, last_name, age) VALUES
('Alice', 'Martin', 22),
('Bob', 'Durand', 24),
('Charlie', 'Bernard', 21),
('Georges', 'GINON', 27);

INSERT INTO course (title, credits) VALUES
('Mathematics', 4),
('Physics', 3),
('Computer Science', 5);

INSERT INTO enrollment (student_id, course_id, grade) VALUES
(1, 1, 15.5),
(1, 3, 18.0),
(2, 2, 12.0),
(3, 3, 16.5);
