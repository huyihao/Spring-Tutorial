ALTER TABLE NOTIFICATION ADD NOTIFIER_NAME VARCHAR(100) NOT NULL;
ALTER TABLE NOTIFICATION ADD OUTER_TITLE VARCHAR(256) NOT NULL;
ALTER TABLE NOTIFICATION ALTER COLUMN STATUE RENAME TO STATUS;