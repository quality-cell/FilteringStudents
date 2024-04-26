CREATE TABLE student_group
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    course INTEGER CHECK (course BETWEEN 1 AND 5) NOT NULL,
    degree varchar(255) NOT NULL
);

INSERT INTO student_group(name, course, degree) VALUES ('8В91', '1', 'BACHELOR');
INSERT INTO student_group(name, course, degree) VALUES ('8В81', '2', 'BACHELOR');
INSERT INTO student_group(name, course, degree) VALUES ('8В71', '3', 'BACHELOR');
INSERT INTO student_group(name, course, degree) VALUES ('8В61', '4', 'BACHELOR');
INSERT INTO student_group(name, course, degree) VALUES ('8ВМ91', '1', 'MASTER');
