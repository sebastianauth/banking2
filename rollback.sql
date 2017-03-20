-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: classpath:/db/changelog/db.changelog-master.xml
-- Ran at: 20.03.17 22:24
-- Against: banking_db_user@jdbc:postgresql://localhost:5432/banking_dev
-- Liquibase version: 3.5.3
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE banking.databasechangeloglock (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP WITHOUT TIME ZONE, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM banking.databasechangeloglock;

INSERT INTO banking.databasechangeloglock (ID, LOCKED) VALUES (1, FALSE);

-- Lock Database
UPDATE banking.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'sebastian-pc (192.168.230.1)', LOCKGRANTED = '2017-03-20 22:24:44.071' WHERE ID = 1 AND LOCKED = FALSE;

-- Create Database Change Log Table
CREATE TABLE banking.databasechangelog (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP WITHOUT TIME ZONE NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Rolling Back ChangeSet: classpath:/db/changelog/changesets/initial.changelog.xml::2::sebastian
DROP TABLE banking.transaction;

ALTER TABLE banking.transaction DROP CONSTRAINT fk_transaction_account;

DELETE FROM banking.databasechangelog WHERE ID = '2' AND AUTHOR = 'sebastian' AND FILENAME = 'classpath:/db/changelog/changesets/initial.changelog.xml';

-- Rolling Back ChangeSet: classpath:/db/changelog/changesets/initial.changelog.xml::1::sebastian
DROP TABLE banking.account;

DELETE FROM banking.databasechangelog WHERE ID = '1' AND AUTHOR = 'sebastian' AND FILENAME = 'classpath:/db/changelog/changesets/initial.changelog.xml';

-- Release Database Lock
UPDATE banking.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

