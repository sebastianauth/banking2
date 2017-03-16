-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: classpath:/db/changelog/db.changelog-master.xml
-- Ran at: 16.03.17 21:55
-- Against: banking_db_user@jdbc:postgresql://localhost:5432/banking_dev
-- Liquibase version: 3.5.3
-- *********************************************************************

-- Lock Database
UPDATE banking.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'sebastian-pc (192.168.230.1)', LOCKGRANTED = '2017-03-16 21:55:05.382' WHERE ID = 1 AND LOCKED = FALSE;

-- Rolling Back ChangeSet: classpath:/db/changelog/changesets/initial.changelog.xml::2::sebastian
DROP TABLE banking.transaction;

DELETE FROM banking.databasechangelog WHERE ID = '2' AND AUTHOR = 'sebastian' AND FILENAME = 'classpath:/db/changelog/changesets/initial.changelog.xml';

-- Release Database Lock
UPDATE banking.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

