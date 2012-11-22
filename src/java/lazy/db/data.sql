/* Insert data into Company-Customer-Supplier schema */
USE lazy;

SET AUTOCOMMIT=0;
INSERT INTO company VALUES (1,'Invest Ltd.'),(2,'Co Inc.'),(3,'Din Don Corp.'),(4,'DDT Found');
COMMIT;

SET AUTOCOMMIT=0;
INSERT INTO customer VALUES (1,1,'Silver Tower'),(2,1,'Java Community'),(3,1,'San Francisco'),(4,2,'LA Endorsed');
COMMIT;

SET AUTOCOMMIT=0;
INSERT INTO supplier VALUES (1,2,'GazProm'),(2,1,'Fishing Roads'),(3,1,'Exon'),(4,1,'Taiwan Devices');
COMMIT;
