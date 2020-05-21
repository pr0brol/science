DROP TABLE IF EXISTS departments CASCADE;
CREATE TABLE departments (
  id                      BIGSERIAL,
  title              VARCHAR(255),
  PRIMARY KEY(id)
);
INSERT INTO departments
(title) VALUES
  ('developers'),
  ('analytics'),
  ('finances'),
  ('engineers');

DROP TABLE IF EXISTS positions CASCADE;
CREATE TABLE positions (
  id                      BIGSERIAL,
  title                VARCHAR(255),
  PRIMARY KEY(id)
);

INSERT INTO positions
(title) VALUES
  ('developer'),
  ('analitic'),
  ('accountant'),
  ('engineer');

DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
  id                    BIGSERIAL,
  first_name            VARCHAR(50) ,
  last_name             VARCHAR(50),
  birthday              DATE,
  age                   SMALLINT,
  remote_work           BOOLEAN,
  address               VARCHAR(255),
  position_id           BIGSERIAL,
  department_id         BIGSERIAL,
  PRIMARY KEY (id),
  FOREIGN KEY (position_id) REFERENCES positions (id),
  FOREIGN KEY (department_id) REFERENCES departments (id)
);


INSERT INTO employees (first_name, last_name, birthday, age, remote_work, address, position_id, department_id)
VALUES
('Ivan','Ivanov','20.05.1998', 20, true, 'Moskow', 1, 1),
('Ivan','Petron','20.06.1998', 22, false, 'SPB', 2, 1),
('Petr','Ivanov','10.05.1998', 10, true, 'Moskow', 1, 2),
('Petr','Petron','10.06.1998', 12, false, 'SPB', 2, 2);

DROP TABLE IF EXISTS calendar CASCADE;
CREATE TABLE calendar (
  id                    BIGSERIAL,
  employee_id           BIGSERIAL,
  day                   DATE,
  status                varchar(20),
  PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employees (id)
);

INSERT INTO calendar (employee_id, day, status)
VALUES
(1,'1.01.1998', 'O'),
(1,'2.01.1998', 'O'),
(1,'3.01.1998', 'O'),
(1,'4.01.1998', 'O'),
(1,'5.01.1998', 'O'),
(1,'6.01.1998', 'P'),
(2,'1.02.1998', 'H'),
(2,'2.02.1998', 'J'),
(2,'3.02.1998', 'H'),
(2,'4.02.1998', 'J'),
(2,'5.02.1998', 'H'),
(2,'6.02.1998', 'J');

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id                    SERIAL,
  name                  VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO roles (name)
VALUES
  ('EMPLOYEE'),
  ('TABELKEEPER'),
  ('DEPARTMENT_ADMIN'),
  ('EMPLOYEES_ADMIN');



--DROP TABLE IF EXISTS users_roles;
--CREATE TABLE users_roles (
--  user_id               INT NOT NULL,
--  role_id               INT NOT NULL,
--  PRIMARY KEY           (user_id, role_id),
--  FOREIGN KEY           (user_id)
--  REFERENCES            users (id),
--  FOREIGN KEY           (role_id)
--  REFERENCES            roles (id)
--);

--
--INSERT INTO users_roles (user_id, role_id)
--VALUES
--(1, 1),
--(1, 2),
--(1, 3);

--
--DROP TABLE IF EXISTS products_images;
--CREATE TABLE products_images (
--  id bigserial          PRIMARY KEY,
--  product_id            BIGINT,
--  path                  VARCHAR(255),
--  FOREIGN KEY (product_id) REFERENCES products(id));
--
--INSERT INTO products_images (product_id, path) VALUES
--(1, 'img_1.jpg'),
--(2, 'img_1.jpg'),
--(3, 'img_1.jpg'),
--(4, 'img_1.jpg'),
--(5, 'img_1.jpg'),
--(6, 'img_1.jpg'),
--(7, 'img_1.jpg'),
--(8, 'img_1.jpg'),
--(9, 'img_1.jpg'),
--(10, 'img_1.jpg');