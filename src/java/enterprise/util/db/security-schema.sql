-- db chema definition
DROP SCHEMA IF EXISTS registry;
CREATE SCHEMA registry;

use security;

CREATE TABLE users(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE KEY (username),
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE authorities(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    authority VARCHAR(255) NOT NULL,
    UNIQUE KEY (username),
    KEY (authority),
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
