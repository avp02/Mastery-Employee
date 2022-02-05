DROP TABLE IF EXISTS genders;
DROP TABLE IF EXISTS employees;

CREATE TABLE genders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gender VARCHAR(20) NOT NULL
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    department_id INTEGER NOT NULL,
    job_title VARCHAR(250) NOT NULL,
    gender_id INTEGER NOT NULL DEFAULT 1,
    date_of_birth TIMESTAMP NOT NULL,
    CONSTRAINT fk_gender_id FOREIGN KEY (gender_id) REFERENCES genders (id)
);