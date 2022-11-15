DROP PROCEDURE IF EXISTS sp_ComplaintAndRepair

GO

CREATE PROCEDURE sp_ComplaintAndRepair

@ID INT, -- New Faculty ID
@Technical_Staff_Name varchar(20),
@Date_Created Date, -- New Faculty name
@Product_ID INT,
@Complaint_ID INT

AS

BEGIN

INSERT INTO Repair_Complaint(ID, Technical_Staff_Name, Date_Created, Product_ID, Complaint_ID)
VALUES(@ID, @Technical_Staff_Name, @Date_Created, @Product_ID, @Complaint_ID)

UPDATE Product
SET Repaired_By = @Technical_Staff_Name
WHERE @ID = ID

END