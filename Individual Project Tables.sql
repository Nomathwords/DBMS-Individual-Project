DROP TABLE IF EXISTS Product1
DROP TABLE IF EXISTS Product2
DROP TABLE IF EXISTS Product3
DROP TABLE IF EXISTS Technical_Staff
DROP TABLE IF EXISTS Worker
DROP TABLE IF EXISTS Quality_Controller

CREATE TABLE Technical_Staff (
   Name varchar(20) PRIMARY KEY,
   Address varchar(50),
   Salary INT,
   Degree varchar(5)
);

CREATE TABLE Worker (
   Name varchar(20) PRIMARY KEY,
   Address varchar(50),
   Salary INT,
   Max_Products INT
);

CREATE TABLE Quality_Controller (
   Name varchar(20) PRIMARY KEY,
   Address varchar(50),
   Salary INT,
   Product_Type varchar(20)
);

CREATE TABLE Product1 (
   ID INT PRIMARY KEY,
   Date varchar(15),
   Time varchar(15),
   Produced_By varchar(20),
   Tested_By varchar(20),
   Repaired_By varchar(20),
   Size varchar(10),
   Software varchar(30),
   FOREIGN KEY (Produced_By) REFERENCES Worker(Name),
   FOREIGN KEY (Tested_By) REFERENCES Quality_Controller(Name),
   FOREIGN KEY (Repaired_By) REFERENCES Technical_Staff(Name)
);

CREATE TABLE Product2 (
   ID INT PRIMARY KEY,
   Date varchar(15),
   Time varchar(15),
   Produced_By varchar(20),
   Tested_By varchar(20),
   Repaired_By varchar(20),
   Size varchar(10),
   Color varchar(10)
   FOREIGN KEY (Produced_By) REFERENCES Worker(Name),
   FOREIGN KEY (Tested_By) REFERENCES Quality_Controller(Name),
   FOREIGN KEY (Repaired_By) REFERENCES Technical_Staff(Name)
);

CREATE TABLE Product3 (
   ID INT PRIMARY KEY,
   Date varchar(15),
   Time varchar(15),
   Produced_By varchar(20),
   Tested_By varchar(20),
   Repaired_By varchar(20),
   Size varchar(10),
   Weight varchar(10),
   FOREIGN KEY (Produced_By) REFERENCES Worker(Name),
   FOREIGN KEY (Tested_By) REFERENCES Quality_Controller(Name),
   FOREIGN KEY (Repaired_By) REFERENCES Technical_Staff(Name)
);

CREATE TABLE Customer (
   Name varchar(20) PRIMARY KEY,
   Address varchar(50)
);