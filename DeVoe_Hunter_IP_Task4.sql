DROP TABLE IF EXISTS Technical_Staff_Degree
DROP TABLE IF EXISTS Repair_Complaint
DROP TABLE IF EXISTS Complaint
DROP TABLE IF EXISTS Accident
DROP TABLE IF EXISTS Product1_Account
DROP TABLE IF EXISTS Product2_Account
DROP TABLE IF EXISTS Product3_Account
DROP TABLE IF EXISTS Purchase
DROP TABLE IF EXISTS Customer
DROP TABLE IF EXISTS Account
DROP TABLE IF EXISTS Repair_Request
DROP TABLE IF EXISTS Product1
DROP TABLE IF EXISTS Product2
DROP TABLE IF EXISTS Product3
DROP TABLE IF EXISTS Product
DROP TABLE IF EXISTS Technical_Staff
DROP TABLE IF EXISTS Worker
DROP TABLE IF EXISTS Quality_Controller
DROP TABLE IF EXISTS Employee

CREATE TABLE Employee (
   Employee_Name varchar(20) PRIMARY KEY,
   Address varchar(50),
   Salary REAL,
   Employee_Type varchar(20)
);

CREATE TABLE Technical_Staff (
   Technical_Staff_Name varchar(20) PRIMARY KEY,
   Position varchar(20),
   FOREIGN KEY (Technical_Staff_Name) REFERENCES Employee(Employee_Name)
);

CREATE TABLE Technical_Staff_Degree (
   Name varchar(20),
   Degree varchar(5),
   CONSTRAINT chk_Degree_Type CHECK(Degree = 'BS' OR Degree = 'MS' OR Degree = 'PHD'),
   PRIMARY KEY (Name, Degree),
   FOREIGN KEY (Name) REFERENCES Technical_Staff(Technical_Staff_Name)
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

CREATE TABLE Product (
   ID INT PRIMARY KEY,
   Date_Created Date,
   Days_Developed INT,
   Produced_By varchar(20),
   Tested_By varchar(20),
   Repaired_By varchar(20),
   Size varchar(20),
   Product_Type INT,

   CONSTRAINT chk_Product_Size CHECK(Size = 'Small' OR Size = 'Medium' OR Size = 'Large'),
   FOREIGN KEY (Produced_By) REFERENCES Worker(Worker_Name),
   FOREIGN KEY (Tested_By) REFERENCES Quality_Controller(Quality_Controller_Name),
   FOREIGN KEY (Repaired_By) REFERENCES Technical_Staff(Technical_Staff_Name)
);

CREATE INDEX Product_Worker ON Product(Produced_By)

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

CREATE INDEX Product2_Color ON Product2(Color)

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
   Complaint_ID INT PRIMARY KEY,
   Date_Created DATE,
   Description varchar(250),
   Treatment varchar(20),
   Customer_Name varchar(20),
   Product_ID INT,
   FOREIGN KEY (Customer_Name) REFERENCES Customer(Name),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);

CREATE TABLE Purchase (
   Customer_Name varchar(20),
   Product_ID INT,
   PRIMARY KEY (Customer_Name, Product_ID),
   FOREIGN KEY (Customer_Name) REFERENCES Customer(Name),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);

CREATE TABLE Accident (
   Accident_Number INT PRIMARY KEY,
   Date_Created DATE,
   Work_Days_Lost INT,
   Product_ID INT,
   Employee_Name varchar(20),
   Employee_Type varChar(20),
   CONSTRAINT chk_Employee_Type CHECK(Employee_Type != 'Quality Controller'),
   FOREIGN KEY (Product_ID) REFERENCES PRODUCT(ID),
   FOREIGN KEY (Employee_Name) REFERENCES Employee(Employee_Name)
);

CREATE INDEX Accident_Date ON Accident(Date_Created)

CREATE TABLE Account (
   Account_Number INT PRIMARY KEY,
   Date_Created DATE,
   Product_ID INT UNIQUE,

   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);

CREATE TABLE Product1_Account (
   Account_Number INT PRIMARY KEY,
   Product_Cost REAL,
   Product_ID INT UNIQUE,

   FOREIGN KEY (Account_Number) REFERENCES Account(Account_Number),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);

CREATE TABLE Product2_Account (
   Account_Number INT PRIMARY KEY,
   Product_Cost REAL,
   Product_ID INT UNIQUE,

   FOREIGN KEY (Account_Number) REFERENCES Account(Account_Number),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);

CREATE TABLE Product3_Account (
   Account_Number INT PRIMARY KEY,
   Product_Cost REAL,
   Product_ID INT UNIQUE,

   FOREIGN KEY (Account_Number) REFERENCES Account(Account_Number),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);

CREATE TABLE Repair_Complaint (
   ID INT PRIMARY KEY,
   Technical_Staff_Name varchar(20),
   Date_Created varchar(10),
   Product_ID INT,
   Complaint_ID INT,

   FOREIGN KEY (Technical_Staff_Name) REFERENCES Technical_Staff(Technical_Staff_Name),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID),
   FOREIGN KEY (Complaint_ID) REFERENCES Complaint(Complaint_ID)
);

CREATE TABLE Repair_Request (
   ID INT PRIMARY KEY,
   Technical_Staff_Name varchar(20),
   Quality_Controller_Name varchar(20),
   Product_ID INT
);

CREATE INDEX Repair_Request_Quality_Controller ON Repair_Request(Quality_Controller_Name asc)
