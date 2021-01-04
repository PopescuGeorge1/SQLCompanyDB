CREATE TABLE employees (
  emp_id INT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  birth_day DATE,
  sex VARCHAR(1),
  salary INT,
  supervisor_id INT,
  branch_id INT
);

CREATE TABLE branch (
  branch_id INT PRIMARY KEY,
  branch_name VARCHAR(30),
  manager_id INT,
  manager_start_date DATE,
  FOREIGN KEY  (manager_id) 
  REFERENCES employees(emp_id) ON DELETE SET NULL
);

ALTER TABLE employees
ADD FOREIGN KEY(branch_id)
REFERENCES branch(branch_id)
ON DELETE SET NULL;

ALTER TABLE employees
ADD FOREIGN KEY(supervisor_id)
REFERENCES employees(emp_id)
ON DELETE SET NULL;

CREATE TABLE client (
  client_id INT PRIMARY KEY,
  client_name VARCHAR(30),
  branch_id INT,
  FOREIGN KEY(branch_id) REFERENCES branch(branch_id) ON DELETE SET NULL
);

CREATE TABLE works_with (
  emp_id INT,
  client_id INT,
  total_sales INT,
  PRIMARY KEY(emp_id, client_id),
  FOREIGN KEY(emp_id) REFERENCES employees(emp_id) ON DELETE CASCADE,
  FOREIGN KEY(client_id) REFERENCES client(client_id) ON DELETE CASCADE
);

CREATE TABLE branch_supplier (
  branch_id INT,
  supplier_name VARCHAR(40),
  supply_type VARCHAR(40),
  PRIMARY KEY(branch_id, supplier_name),
  FOREIGN KEY(branch_id) REFERENCES branch(branch_id) ON DELETE CASCADE
);

INSERT INTO employees VALUES(100, 'Alex', 'Stan', '1967-11-17', 'M', 250000, NULL, NULL);

INSERT INTO branch VALUES(1, 'Corporate', 100, '2006-02-09');

UPDATE employees
SET branch_id = 1
WHERE emp_id = 100;

INSERT INTO employees VALUES(101, 'Ion', 'Popescu', '1961-05-11', 'F', 110000, 100, 1);

INSERT INTO employees VALUES(102, 'Mihai', 'Vasile', '1964-03-15', 'M', 75000, 100, NULL);

INSERT INTO branch VALUES(2, 'Scranton', 102, '1992-04-06');

UPDATE employees
SET branch_id = 2
WHERE emp_id = 102;

INSERT INTO employees VALUES(103, 'Andreea', 'Ungureanu', '1971-06-25', 'F', 63000, 102, 2);
INSERT INTO employees VALUES(104, 'Ioana', 'Carstea', '1980-02-05', 'F', 55000, 102, 2);
INSERT INTO employees VALUES(105, 'Dragos', 'Ionescu', '1958-02-19', 'M', 69000, 102, 2);

INSERT INTO employees VALUES(106, 'Ion', 'Nedelcu', '1969-09-05', 'M', 78000, 100, NULL);

INSERT INTO branch VALUES(3, 'Stamford', 106, '1998-02-13');

UPDATE employees
SET branch_id = 3
WHERE emp_id = 106;

INSERT INTO employees VALUES(107, 'Valentin', 'Pana', '1973-07-22', 'M', 65000, 106, 3);
INSERT INTO employees VALUES(108, 'Adrian', 'Baltaretu', '1978-10-01', 'M', 71000, 106, 3);

INSERT INTO branch_supplier VALUES(2, 'Biroticienii', 'Paper');
INSERT INTO branch_supplier VALUES(2, 'Uni-ball', 'Writing Utensils');
INSERT INTO branch_supplier VALUES(3, 'MaxMember', 'Paper');
INSERT INTO branch_supplier VALUES(2, 'Rieco', 'Custom Forms');
INSERT INTO branch_supplier VALUES(3, 'Uni-ball', 'Writing Utensils');
INSERT INTO branch_supplier VALUES(3, 'Biroticienii', 'Paper');
INSERT INTO branch_supplier VALUES(3, 'Dolex', 'Custom Forms');

INSERT INTO client VALUES(400, 'C.N. Al. Lahovari', 2);
INSERT INTO client VALUES(401, 'Carturesti', 2);
INSERT INTO client VALUES(402, 'FedEx', 3);
INSERT INTO client VALUES(403, 'NNDKP', 3);
INSERT INTO client VALUES(404, 'Scranton Whitepages', 2);
INSERT INTO client VALUES(405, 'Libertatea Newspaper', 3);
INSERT INTO client VALUES(406, 'FedEx', 2);

INSERT INTO works_with VALUES(105, 400, 45000);
INSERT INTO works_with VALUES(102, 401, 286000);
INSERT INTO works_with VALUES(108, 402, 19200);
INSERT INTO works_with VALUES(107, 403, 12000);
INSERT INTO works_with VALUES(108, 403, 7000);
INSERT INTO works_with VALUES(105, 404, 48000);
INSERT INTO works_with VALUES(107, 405, 28000);
INSERT INTO works_with VALUES(102, 406, 49000);
INSERT INTO works_with VALUES(105, 406, 630000);