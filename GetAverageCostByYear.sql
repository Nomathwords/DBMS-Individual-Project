DROP PROCEDURE IF EXISTS sp_getAverageCostByYear
GO

CREATE PROCEDURE sp_getAverageCostByYear

@StartDate Date,
@EndDate Date

AS

BEGIN

SELECT Product_Cost FROM Product1_Account JOIN Product ON Product1_Account.Product_ID = Product.ID WHERE Product.Date_Created BETWEEN '2022-01-01' AND '2022-12-31'
UNION SELECT Product_Cost FROM Product2_Account JOIN Product ON Product2_Account.Product_ID = Product.ID WHERE Product.Date_Created BETWEEN '2022-01-01' AND '2022-12-31'
UNION SELECT Product_Cost FROM Product3_Account JOIN Product ON Product3_Account.Product_ID = Product.ID WHERE Product.Date_Created BETWEEN '2022-01-01' AND '2022-12-31'

END