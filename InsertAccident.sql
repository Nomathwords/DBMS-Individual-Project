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

SET @Employee_TYPE = (SELECT Employee_Type FROM Employee WHERE Employee_Name = @Employee_Name)

INSERT INTO Accident(Accident_Number, Date_Created, Work_Days_Lost, Product_ID, Employee_Name, Employee_Type) 
VALUES(@Accident_Number, @Date_Created, @Work_Days_Lost, @Product_ID, @Employee_Name, @Employee_Type)

END