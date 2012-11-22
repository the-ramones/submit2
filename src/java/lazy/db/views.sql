/* Simple views for Company-Customer-Supplier schema */
USE lazy;
DROP VIEW IF EXISTS company_supplier_list;
DROP VIEW IF EXISTS company_customer_list;
DROP VIEW IF EXISTS supplier_list;
DROP VIEW IF EXISTS customer_list;

CREATE VIEW customer_list
AS
SELECT cust.customer_id as 'CID', cust.name as 'NAME', GROUP_CONCAT(comp.name SEPARATOR ', ') as 'COMPANIES'
FROM customer as cust, company as comp
WHERE cust.company_id = comp.company_id
GROUP BY cust.customer_id
ORDER BY cust.name;

CREATE VIEW company_supplier_list
AS
SELECT comp.company_id as 'CID', comp.name as 'TITLE',
    GROUP_CONCAT(supp.name SEPARATOR ', ') as 'SUPLIERS'
FROM company as comp
    LEFT JOIN supplier as supp ON supp.company_id = comp.company_id
GROUP BY comp.company_id
ORDER BY comp.company_id;

CREATE VIEW company_customer_list
AS
SELECT comp.company_id as 'CID', comp.name as 'TITLE',
    GROUP_CONCAT(cust.name SEPARATOR ', ') as 'CUSTOMERS'
FROM company as comp
    LEFT JOIN customer as cust ON cust.company_id = comp.company_id
GROUP BY comp.company_id
ORDER BY comp.company_id;

CREATE VIEW supplier_list
AS
SELECT supp.supplier_id as 'SID', supp.name as 'NAME', GROUP_CONCAT(comp.name SEPARATOR ', ') as 'COMPANIES'
FROM supplier as supp, company as comp
WHERE supp.company_id = comp.company_id
GROUP BY supp.supplier_id
ORDER BY supp.name;