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

SET @Product_TYPE = (SELECT Product_Type FROM Product WHERE ID = @Product_ID)

INSERT INTO Account(Account_Number, Date_Created) 
VALUES(@Account_Number, @Date_Created)

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