/* SQL for Company-Customer-Supplier database */

-- Structure for table 'customer'
DROP SCHEMA IF EXISTS lazy;
CREATE SCHEMA lazy;

USE lazy;

CREATE TABLE company(
    company_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (company_id),
    KEY idx_comppany_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE customer(
    customer_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    company_id INTEGER UNSIGNED NOT NULL,
    name VARCHAR(100) NOT NULL,
    KEY idx_customer_name (name),
    PRIMARY KEY (customer_id),
    CONSTRAINT fk_customer_company_id FOREIGN KEY (company_id) REFERENCES company (company_id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE supplier(
    supplier_id INTEGER UNSIGNED NOT NULL  AUTO_INCREMENT,
    company_id INTEGER UNSIGNED NOT NULL ,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (supplier_id),
    KEY idx_supplier_name (name),
    CONSTRAINT fx_supplier_company_id FOREIGN KEY (company_id) REFERENCES company (company_id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


