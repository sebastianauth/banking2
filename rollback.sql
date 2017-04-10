-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: classpath:/db/changelog/db.changelog-master.xml
-- Ran at: 10/04/17 23:52
-- Against: banking_db_user@jdbc:postgresql://localhost:5432/banking_dev?currentSchema=banking
-- Liquibase version: 3.5.3
-- *********************************************************************

-- Lock Database
UPDATE banking.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'sebastian-pc (192.168.230.1)', LOCKGRANTED = '2017-04-10 23:52:45.603' WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE banking.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

