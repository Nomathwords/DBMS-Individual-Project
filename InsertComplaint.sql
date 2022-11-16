DROP PROCEDURE IF EXISTS sp_Complaint

GO

CREATE PROCEDURE sp_Complaint

@Complaint_ID INT, -- New Faculty ID
@Date_Created DATE,
@Description varchar(250), -- New Faculty name
@Treatment varchar(100),
@Customer_Name varchar(20),
@Product_ID INT

AS

BEGIN

DECLARE @BANKRUPTCY INT

SET @BANKRUPTCY = (SELECT COUNT(Product_ID) FROM Purchase WHERE Customer_Name = @Customer_Name AND Product_ID = @Product_ID)

IF @BANKRUPTCY = 0
    BEGIN
        RAISERROR ('%s has not bought a product with the ID of %d', 14, 1, @Customer_Name, @Product_ID)
    END

ELSE
    BEGIN
        INSERT INTO Complaint(Complaint_ID, Date_Created, Description, Treatment, Customer_Name, Product_ID)
        VALUES(@Complaint_ID, @Date_Created, @Description, @Treatment, @Customer_Name, @Product_ID)
    END
END