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

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id                    SERIAL,
  name                  VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO roles (name)
VALUES
  ('ROLE_EMPLOYEE'),
  ('ROLE_TABELKEEPER'),
  ('ROLE_DEPARTMENT_ADMIN'),
  ('ROLE_EMPLOYEES_ADMIN');

DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
  id                    BIGSERIAL,
  first_name            VARCHAR(50) ,
  last_name             VARCHAR(50) NOT NULL UNIQUE,
  birthday              DATE,
  age                   SMALLINT,
  remote_work           BOOLEAN,
  address               VARCHAR(255),
  position_id           BIGSERIAL,
  department_id         BIGSERIAL,
  password              VARCHAR(80),
  PRIMARY KEY (id),
  FOREIGN KEY (position_id) REFERENCES positions (id),
  FOREIGN KEY (department_id) REFERENCES departments (id)
);


INSERT INTO employees (first_name, last_name, birthday, age, remote_work, address, position_id, department_id, password)
VALUES
('Ivan','Ivanov','20.05.1998', 20, true, 'Moskow', 1, 1, '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('Ivan','Petrov','20.06.1998', 22, false, 'SPB', 2, 1, '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('Petr','Sidorov','10.05.1998', 10, true, 'Moskow', 1, 2, '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('Petr','Pupkin','10.06.1998', 12, false, 'SPB', 2, 2, '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

  DROP TABLE IF EXISTS employees_roles;
CREATE TABLE employees_roles (
  employee_id           BIGSERIAL NOT NULL,
  role_id               SERIAL NOT NULL,
  PRIMARY KEY           (employee_id, role_id),
  FOREIGN KEY           (employee_id)
  REFERENCES            employees (id),
  FOREIGN KEY           (role_id)
  REFERENCES            roles (id)
);

INSERT INTO employees_roles (employee_id, role_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

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
