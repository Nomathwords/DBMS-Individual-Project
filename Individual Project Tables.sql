DROP TABLE IF EXISTS Product1
DROP TABLE IF EXISTS Product2
DROP TABLE IF EXISTS Product3
DROP TABLE IF EXISTS Technical_Staff_Degree
DROP TABLE IF EXISTS Repair_Complaint
DROP TABLE IF EXISTS Complaint
DROP TABLE IF EXISTS Accident
DROP TABLE IF EXISTS Product1_Account
DROP TABLE IF EXISTS Product2_Account
DROP TABLE IF EXISTS Product3_Account
DROP TABLE IF EXISTS Purchase
DROP TABLE IF EXISTS Product
DROP TABLE IF EXISTS Technical_Staff
DROP TABLE IF EXISTS Worker
DROP TABLE IF EXISTS Quality_Controller
DROP TABLE IF EXISTS Employee
DROP TABLE IF EXISTS Customer
DROP TABLE IF EXISTS Account
DROP TABLE IF EXISTS Repair_Request

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
)
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
   Accident_Number INT,
   Date_Created DATE,
   Work_Days_Lost varchar(3),
   Product_ID INT,
   Employee_Name varchar(20),
   Employee_Type varChar(20),
   CONSTRAINT chk_Employee_Type CHECK(Employee_Type != 'Quality Controller'),
   FOREIGN KEY (Product_ID) REFERENCES PRODUCT(ID),
   FOREIGN KEY (Employee_Name) REFERENCES Employee(Employee_Name)
);
CREATE TABLE Account (
   Account_Number INT PRIMARY KEY,
   Date_Created DATE
);
CREATE TABLE Product1_Account (
   Account_Number INT PRIMARY KEY,
   Product_Cost REAL,
   Product_ID INT,
   FOREIGN KEY (Account_Number) REFERENCES Account(Account_Number),
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);
CREATE TABLE Product2_Account (
   Account_Number INT PRIMARY KEY,
   Product_Cost INT,
   Product_ID INT,
   FOREIGN KEY (Product_ID) REFERENCES Product(ID)
);
CREATE TABLE Product3_Account (
   Account_Number INT PRIMARY KEY,
   Product_Cost INT,
   Product_ID INT,
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
----------------------------------------------------------------------------------------
-- Insert employees
INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type)
VALUES('Hunter', '2325 Louise Ln', 10000, 'Technical Staff')

INSERT INTO Technical_Staff(Technical_Staff_Name, Position)
VALUES('Hunter', 'IT')

INSERT INTO Technical_Staff_Degree(Name, Degree)
VALUES ('Hunter', 'BS')

INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type)
VALUES('Steven', '1321 E 24th St', 50000, 'Technical Staff')

INSERT INTO Technical_Staff(Technical_Staff_Name, Position)
VALUES('Steven', 'Manager')

INSERT INTO Technical_Staff_Degree(Name, Degree)
VALUES ('Steven', 'BS')

INSERT INTO Technical_Staff_Degree(Name, Degree)
VALUES ('Steven', 'MS')

INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type)
VALUES('Jackson', '1410 Wisteria Ave', 32000, 'Worker')

INSERT INTO Worker(Worker_Name, Max_Products)
VALUES('Jackson', 23)

INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type)
VALUES('Dieko', '1309 Traditions Way', 28000, 'Worker')

INSERT INTO Worker(Worker_Name, Max_Products)
VALUES('Dieko', 20)

INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type)
VALUES('Ansley', '1410 Wisteria Ave', 10000, 'Quality Controller')

INSERT INTO Quality_Controller(Quality_Controller_Name, Product_Type)
VALUES('Ansley', 'Toys')

INSERT INTO Employee(Employee_Name, Address, Salary, Employee_Type)
VALUES('Ronald', '1100 S Ocean Blvd, Palm Beach', 100000, 'Quality Controller')

INSERT INTO Quality_Controller(Quality_Controller_Name, Product_Type)
VALUES('Ronald', 'Flags')

-- Insert Products
INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(1, '11/13/2022', 3, 'Jackson', 'Ansley', null, 'Large', 1)

INSERT INTO Product1(Product1_ID, Software)
VALUES(1, 'Java')

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(4, '10/25/2019', 65, 'Dieko', 'Ronald', null, 'Extra Small', 1)

INSERT INTO Product1(Product1_ID, Software)
VALUES(4, 'C++')

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(5, '07/04/2016', 12, 'Dieko', 'Ansley', 'Steven', 'Medium', 1)

INSERT INTO Product1(Product1_ID, Software)
VALUES(5, 'Ruby')

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(2, '11/10/2022', 45, 'Jackson', 'Ansley', 'Hunter', 'Small', 2)

INSERT INTO Product2(Product2_ID, Color)
VALUES(2, 'Green')

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(6, '12/31/1999', 365, 'Dieko', 'Ronald', null, 'Extra Large', 2)

INSERT INTO Product2(Product2_ID, Color)
VALUES(6, 'Black')

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(7, '02/24/2002', 61, 'Jackson', 'Ronald', 'Steven', 'Small', 2)

INSERT INTO Product2(Product2_ID, Color)
VALUES(7, 'Red')

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(3, '11/10/2022', 45, 'Jackson', 'Ansley', 'Hunter', 'Medium', 3)

INSERT INTO Product3(Product3_ID, Weight)
VALUES(3, 100.75)

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(8, '08/02/2005', 02, 'Dieko', 'Ansley', null, 'Small', 3)

INSERT INTO Product3(Product3_ID, Weight)
VALUES(8, 5.25)

INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
VALUES(9, '10/23/2007', 10, 'Dieko', 'Ronald', null, 'Large', 3)

INSERT INTO Product3(Product3_ID, Weight)
VALUES(9, 107)

-- Insert customers associated with products
INSERT INTO Customer(Name, Address)
VALUES('Rebecca', 'Bumfuckville')

INSERT INTO Purchase(Customer_Name, Product_ID)
VALUES('Rebecca', 1)

INSERT INTO Customer(Name, Address)
VALUES('Kyle', '1410 Wisteria Ave')

INSERT INTO Purchase(Customer_Name, Product_ID)
VALUES('Kyle', 1)

-- Create product accounts
INSERT INTO Account(Account_Number, Date_Created)
VALUES(1, '11/13/2022')

INSERT INTO Product1_Account(Account_Number, Product_Cost, Product_ID)
VALUES(1, 100.75, 1)

INSERT INTO Account(Account_Number, Date_Created)
VALUES(23, '11/12/2022')

INSERT INTO Product2_Account(Account_Number, Product_Cost, Product_ID)
VALUES(23, 1000.54, 2)

-- Create complaint
--INSERT INTO Complaint(Complaint_ID, Date_Created, Description, Treatment, Customer_Name, Product_ID)
--VALUES(1, '11/13/2022', 'Screen is faded', 'Screen replacement', 'Rebecca', 1)

-- Create accident
INSERT INTO Accident(Accident_Number, Date_Created, Work_Days_Lost, Product_ID, Employee_Name, Employee_Type) 
VALUES(1, '08/25/2000', 45, 2, 'Hunter', 'Technical Staff')
-------------------------------------------------------------------------------------------------------------
/*SELECT * FROM Employee
SELECT * FROM Technical_Staff
SELECT * FROM Technical_Staff_Degree
SELECT * FROM Worker
SELECT * FROM Quality_Controller
SELECT * FROM Product
SELECT * FROM Product1
SELECT * FROM Product2
SELECT * FROM Product3
SELECT * FROM Customer
SELECT * FROM Purchase 
SELECT * FROM Account
SELECT * FROM Product1_Account
SELECT * FROM Product2_Account
SELECT * FROM Product3_Account
SELECT * FROM Complaint
SELECT * FROM Accident */