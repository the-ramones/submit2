-- dump data 

use registry;

INSERT INTO users VALUES (1, 'Dan Sammerson', 'manager'),
        (2, 'Kathy LaFael', 'secretary'),
        (3, 'Peter Norton', 'director');

INSERT INTO ops VALUES (1, 'INSERT', 'data insertion into enterprise db'),
        (2, 'DELETE', 'deletion from enterprise db'),
        (3, 'UPDATE', 'data updates in enterprise db'),
        (4, 'READ', 'data reading from enterprise db');

INSERT INTO registers VALUES(1,1,1,TIMESTAMP('2013-01-21 14:11:09')),
        VALUES(2,2,2,TIMESTAMP('2013-03-21 13:11:09')),
        VALUES(3,3,3,TIMESTAMP('2013-04-21 12:11:01')),
        VALUES(4,1,4,TIMESTAMP('2013-05-23 06:21:02')),
        VALUES(5,2,1,TIMESTAMP('2013-06-03 07:31:06')),
        VALUES(6,3,2,TIMESTAMP('2013-07-30 08:41:05')),
        VALUES(7,1,3,TIMESTAMP('2013-02-17 09:51:04'));
