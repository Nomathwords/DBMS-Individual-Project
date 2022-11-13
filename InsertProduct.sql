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