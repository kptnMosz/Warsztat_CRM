Create TABLE employees (
  id      INTEGER unsigned auto_increment primary key,
  name    VARCHAR(20),
  surname VARCHAR(20),
  adress  VARCHAR(250),
  phone   VARCHAR(20),
  notes   VARCHAR(250),
  wage    DECIMAL
);

CREATE TABLE customers (
  id       INTEGER unsigned auto_increment primary key,
  name     VARCHAR(20),
  surname  VARCHAR(20),
  birthday DATE,
  email    varchar(50) not null,
  constraint customers_email_uindex
  unique (email)
);

CREATE TABLE vehicles (
  id              INTEGER unsigned auto_increment primary key,
  model           VARCHAR(20),
  brand           VARCHAR(20),
  produced        INTEGER,
  registration    VARCHAR(20),
  next_inspection DATE,
  customer_id     INTEGER unsigned,
  FOREIGN KEY (customer_id) REFERENCES customers(id)

);


CREATE TABLE statuses (
  id   INTEGER unsigned auto_increment primary key,
  name VARCHAR(20)
);


CREATE TABLE orders (
  id           INTEGER unsigned auto_increment primary key,
  acceptance   DATE,
  planned_fix  DATE,
  start_fix    DATE,
  problem_desc varchar(1000),
  fix_desc     varchar(1000),
  status_id    INTEGER unsigned,
  vehicle_id   INTEGER unsigned,
  price        DECIMAL,
  parts_cost   DECIMAL,
  labor_cost   DECIMAL,
  workhours    integer,
  employee_id  INTEGER unsigned,
  FOREIGN KEY (vehicle_id) REFERENCES vehicles (id),
  FOREIGN KEY (employee_id) REFERENCES employees (id),
  FOREIGN KEY (status_id) REFERENCES statuses (id)
);
