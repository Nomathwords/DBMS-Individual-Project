DROP PROCEDURE IF EXISTS sp_enterAccident

GO

CREATE PROCEDURE sp_enterAccident

@Accident_Number INT,
@Date_Created Date,
@Work_Days_Lost INT,
@Product_ID INT,
@Employee_Name varchar(20)

AS

BEGIN

DECLARE @Employee_Type varchar(20)

-- Get the employee type of who had the accident
SET @Employee_TYPE = (SELECT Employee_Type FROM Employee WHERE Employee_Name = @Employee_Name)

INSERT INTO Accident(Accident_Number, Date_Created, Work_Days_Lost, Product_ID, Employee_Name, Employee_Type) 
VALUES(@Accident_Number, @Date_Created, @Work_Days_Lost, @Product_ID, @Employee_Name, @Employee_Type)

END

-------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_enterAccount

GO

CREATE PROCEDURE sp_enterAccount

@Account_Number INT,
@Date_Created Date,
@Product_Cost REAL,
@Product_ID INT

AS

BEGIN

DECLARE @Product_Type INT

INSERT INTO Account(Account_Number, Date_Created, Product_ID) 
VALUES(@Account_Number, @Date_Created, @Product_ID)

SET @Product_Type = (SELECT Product_Type FROM Product WHERE ID = @Product_ID)

IF @Product_Type = 1
    BEGIN
        INSERT INTO Product1_Account(Account_Number, Product_Cost, Product_ID) 
        VALUES(@Account_Number, @Product_Cost, @Product_ID)
    END

IF @Product_Type = 2
    BEGIN
        INSERT INTO Product2_Account(Account_Number, Product_Cost, Product_ID) 
        VALUES(@Account_Number, @Product_Cost, @Product_ID)
    END

IF @Product_Type = 3
    BEGIN
        INSERT INTO Product3_Account(Account_Number, Product_Cost, Product_ID) 
        VALUES(@Account_Number, @Product_Cost, @Product_ID)
    END
END

------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_Complaint

GO

CREATE PROCEDURE sp_Complaint

@Complaint_ID INT,
@Date_Created DATE,
@Description varchar(250),
@Treatment varchar(100),
@Customer_Name varchar(20),
@Product_ID INT

AS

BEGIN

DECLARE @NUMBER INT -- Check if the customer has actually purchased the product before

SET @NUMBER = (SELECT COUNT(Product_ID) FROM Purchase WHERE Customer_Name = @Customer_Name AND Product_ID = @Product_ID)

IF @NUMBER = 0
    BEGIN
        RAISERROR ('%s has not bought a product with the ID of %d', 14, 1, @Customer_Name, @Product_ID)
    END

ELSE
    BEGIN
        INSERT INTO Complaint(Complaint_ID, Date_Created, Description, Treatment, Customer_Name, Product_ID)
        VALUES(@Complaint_ID, @Date_Created, @Description, @Treatment, @Customer_Name, @Product_ID)
    END
END

-----------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_enterProduct

GO

CREATE PROCEDURE sp_enterProduct

@ID INT, -- New Faculty ID
@Date_Created Date, -- New Faculty name
@Days_Developed INT, -- New Faculty department ID
@Produced_By varchar(20),
@Tested_By varchar(20),
@Repaired_By varchar(20),
@Size varchar(20),
@Product_Type INT

AS

BEGIN

IF @Repaired_By = 'NULL'
    INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
    VALUES(@ID, @Date_Created, @Days_Developed, @Produced_By, @Tested_By, NULL, @Size, @Product_Type)

ELSE
    INSERT INTO Product(ID, Date_Created, Days_Developed, Produced_By, Tested_By, Repaired_By, Size, Product_Type)
    VALUES(@ID, @Date_Created, @Days_Developed, @Produced_By, @Tested_By, @Repaired_By, @Size, @Product_Type)

END

------------------------------------------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_ComplaintAndRepair

GO

CREATE PROCEDURE sp_ComplaintAndRepair

@ID INT,
@Technical_Staff_Name varchar(20),
@Date_Created Date,=
@Product_ID INT,
@Complaint_ID INT

AS

BEGIN

INSERT INTO Repair_Complaint(ID, Technical_Staff_Name, Date_Created, Product_ID, Complaint_ID)
VALUES(@ID, @Technical_Staff_Name, @Date_Created, @Product_ID, @Complaint_ID)

-- Update the Product's Technical Staff Name (it is now being repaired)
UPDATE Product
SET Repaired_By = @Technical_Staff_Name
WHERE @ID = ID

END