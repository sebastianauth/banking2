-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: classpath:/db/changelog/db.changelog-master.xml
-- Ran at: 15/04/17 15:08
-- Against: banking_db_user@jdbc:postgresql://localhost:5432/banking_dev?currentSchema=banking
-- Liquibase version: 3.5.3
-- *********************************************************************

-- Lock Database
UPDATE banking.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'sebastian-pc (192.168.230.1)', LOCKGRANTED = '2017-04-15 15:08:00.504' WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE banking.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

