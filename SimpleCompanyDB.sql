CREATE TABLE employees (
  emp_id INT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  birthday DATE,
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
