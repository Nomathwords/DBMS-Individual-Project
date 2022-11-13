/*DROP TABLE IF EXISTS Product1
DROP TABLE IF EXISTS Product2
DROP TABLE IF EXISTS Product3
DROP TABLE IF EXISTS Product
DROP TABLE IF EXISTS Technical_Staff_Degree
DROP TABLE IF EXISTS Repair_Complaint
DROP TABLE IF EXISTS Technical_Staff
DROP TABLE IF EXISTS Worker
DROP TABLE IF EXISTS Quality_Controller
DROP TABLE IF EXISTS Customer
DROP TABLE IF EXISTS Complaint
DROP TABLE IF EXISTS Accident
DROP TABLE IF EXISTS Product1_Account
DROP TABLE IF EXISTS Product2_Account
DROP TABLE IF EXISTS Product3_Account
DROP TABLE IF EXISTS Employee
DROP TABLE IF EXISTS Purchase */

DROP TABLE IF EXISTS Customer
DROP TABLE IF EXISTS Product3
DROP TABLE IF EXISTS Product2
DROP TABLE IF EXISTS Product1
DROP TABLE IF EXISTS Product
DROP TABLE IF EXISTS Purchase


/*CREATE TABLE Employee (
   Employee_Name varchar(20) PRIMARY KEY,
   Address varchar(50),
   Salary INT,
   Employee_Type varchar(20)
);

CREATE TABLE Technical_Staff (
   Technical_Staff_Name varchar(20) PRIMARY KEY,
   Position varchar(20),

   FOREIGN KEY (Technical_Staff_Name) REFERENCES Employee(Employee_Name)
);

CREATE TABLE Worker (
   Worker_Name varchar(20) PRIMARY KEY,
   Max_Products INT,

   FOREIGN KEY (Worker_Name) REFERENCES Employee(Employee_Name)
);

CREATE TABLE Quality_Controller (
   Quality_Controller_Name varchar(20) PRIMARY KEY,
   Product_Type varchar(20),

   FOREIGN KEY (Quality_Controller_Name) REFERENCES Employee(Employee_Name)
);

CREATE TABLE Technical_Staff_Degree (
   Name varchar(20),
   Degree varchar(5),

   PRIMARY KEY (Name, Degree),
   FOREIGN KEY (Name) REFERENCES Technical_Staff(Technical_Staff_Name)
)

CREATE TABLE Product (
   ID INT PRIMARY KEY,
   Date_Created Date,
   Days_Developed INT,
   Produced_By varchar(20),
   Tested_By varchar(20),
   Repaired_By varchar(20),
   Size varchar(20),
   Product_Type INT,

   FOREIGN KEY (Produced_By) REFERENCES Worker(Worker_Name),
   FOREIGN KEY (Tested_By) REFERENCES Quality_Controller(Quality_Controller_Name),
   FOREIGN KEY (Repaired_By) REFERENCES Technical_Staff(Technical_Staff_Name)
);

CREATE TABLE Product1 (
   Product1_ID INT PRIMARY KEY,
   Software varchar(30),

   FOREIGN KEY (Product1_ID) REFERENCES Product(ID)
);

CREATE TABLE Product2 (
   Product2_ID INT PRIMARY KEY,
   Color varchar(10),

   FOREIGN KEY (Product2_ID) REFERENCES Product(ID)
);

CREATE TABLE Product3 (
   Product3_ID INT PRIMARY KEY,
   Weight varchar(10),

   FOREIGN KEY (Product3_ID) REFERENCES Product(ID)
);

 CREATE TABLE Customer (
   Name varchar(20) PRIMARY KEY,
   Address varchar(50)
);

CREATE TABLE Complaint (
   ID INT PRIMARY KEY,
   Date varchar(10),
   Description varchar(250),
   Treatment varchar(20)
);

CREATE TABLE Purchase (
   Customer_Name varchar(20),
   Product_ID INT PRIMARY KEY,

   FOREIGN KEY (Customer_Name) REFERENCES Customer(Name),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);

 CREATE TABLE Accident (
   Accident_Number varchar(6),
   Date varchar(10),
   Work_Days_Lost varchar(3)
);

CREATE TABLE Product1_Account (
   Account_Number INT PRIMARY KEY,
   Date varchar(10),
   Product_Cost INT
);

CREATE TABLE Product2_Account (
   Account_Number INT PRIMARY KEY,
   Date varchar(10),
   Product_Cost INT
);

CREATE TABLE Product3_Account (
   Account_Number INT PRIMARY KEY,
   Date varchar(10),
   Product_Cost INT
);

CREATE TABLE Repair_Complaint (
   ID INT PRIMARY KEY,
   Technical_Staff_Name varchar(20),
   Date_Repaired varchar(10),

   FOREIGN KEY (Technical_Staff_Name) REFERENCES Technical_Staff(Technical_Staff_Name),
); */

/*INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(1, '11/13/2022', 3, 'Jackson', 'Ansley', NULL, 'Large', 1)*/

SELECT * FROM Product