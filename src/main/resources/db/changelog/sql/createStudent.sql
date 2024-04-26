CREATE TABLE student
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    group_id BIGSERIAL REFERENCES student_group (id) on DELETE CASCADE,
    second_name varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    surname varchar(255)  NOT NULL,
    gender varchar(10) NOT NULL,
    status varchar(50) NOT NULL,
    funding_type varchar(50) NOT NULL
);

INSERT INTO student(group_id, second_name, name, surname, gender, status, funding_type) VALUES ('1', 'Романов', 'Максим', 'Юрьевич', 'MALE', 'STUDENT', 'BUDGET');
INSERT INTO student(group_id, second_name, name, surname, gender, status, funding_type) VALUES ('2', 'Человек', 'Паук', 'Бетменович', 'MALE', 'STUDENT', 'BUDGET');
INSERT INTO student(group_id, second_name, name, surname, gender, status, funding_type) VALUES ('3', 'Железный', 'Человек', 'Рахманович', 'MALE', 'STUDENT', 'BUDGET');
INSERT INTO student(group_id, second_name, name, surname, gender, status, funding_type) VALUES ('4', 'Крюгер', 'Иван', 'Михайлович', 'MALE', 'STUDENT', 'CONTRACTUAL');
INSERT INTO student(group_id, second_name, name, surname, gender, status, funding_type) VALUES ('5', 'Романова', 'Вдова', 'Игоревна', 'FEMALE', 'STUDENT', 'CONTRACTUAL');
