/*
============================================================================
  Copyright The Ohio State University Research Foundation, The University of Chicago - 
	Argonne National Laboratory, Emory University, SemanticBits LLC, and 
	Ekagra Software Technologies Ltd.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagrid-general/LICENSE.txt for details.
============================================================================
*/
DROP SEQUENCE CSM_APPLICATI_APPLICATION__SEQ;

DROP TABLE CSM_APPLICATION CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_GROUP_GROUP_ID_SEQ;

DROP TABLE CSM_GROUP CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_PG_PE_PG_PE_ID;

DROP TRIGGER SET_CSM_PG_PE_UPDATE_DATE;

DROP SEQUENCE CSM_PG_PE_PG_PE_ID_SEQ;

DROP TABLE CSM_PG_PE CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_PRIVILEGE_PRIVILEGE_ID_SEQ;

DROP TABLE CSM_PRIVILEGE CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_PROTECTIO_PROTECTION_E_SEQ;

DROP TABLE CSM_PROTECTION_ELEMENT CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_PROTECTIO_PROTECTION_G_SEQ;

DROP TABLE CSM_PROTECTION_GROUP CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_ROLE_ROLE_ID_SEQ;

DROP TABLE CSM_ROLE CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_ROLE_PRIV_ROLE_PRIVILE;

DROP TRIGGER SET_CSM_ROLE_PRIV_UPDATE_DATE;

DROP SEQUENCE CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ;

DROP TABLE CSM_ROLE_PRIVILEGE CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_USER_USER_ID_SEQ;

DROP TABLE CSM_USER CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_USER_GROU_USER_GROUP_I;

DROP SEQUENCE CSM_USER_GROU_USER_GROUP_I_SEQ;

DROP TABLE CSM_USER_GROUP CASCADE CONSTRAINTS
;

DROP SEQUENCE CSM_USER_GROU_USER_GROUP_R_SEQ;

DROP TABLE CSM_USER_GROUP_ROLE_PG CASCADE CONSTRAINTS
;

DROP TRIGGER SET_CSM_USER_PE_USER_PROTECTIO;

DROP TRIGGER SET_CSM_USER_PE_UPDATE_DATE;

DROP SEQUENCE CSM_USER_PE_USER_PROTECTIO_SEQ;

DROP TABLE CSM_USER_PE CASCADE CONSTRAINTS
;


CREATE TABLE CSM_APPLICATION ( 
	APPLICATION_ID NUMBER(38) NOT NULL,
	APPLICATION_NAME VARCHAR2(255) NOT NULL,
	APPLICATION_DESCRIPTION VARCHAR2(200) NOT NULL,
	DECLARATIVE_FLAG NUMBER(1) NOT NULL,
	ACTIVE_FLAG NUMBER(1) DEFAULT '0' NOT NULL ,
	UPDATE_DATE DATE NOT NULL,
	DATABASE_URL VARCHAR2(100),
	DATABASE_USER_NAME VARCHAR2(100),
	DATABASE_PASSWORD VARCHAR2(100),
	DATABASE_DIALECT VARCHAR2(100),
	DATABASE_DRIVER VARCHAR2(100)
) 
;
CREATE SEQUENCE CSM_APPLICATI_APPLICATION__SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_GROUP ( 
	GROUP_ID NUMBER(38) NOT NULL,
	GROUP_NAME VARCHAR2(255) NOT NULL,
	GROUP_DESC VARCHAR2(200),
	UPDATE_DATE DATE NOT NULL,
	APPLICATION_ID NUMBER(38) NOT NULL
) 
;
CREATE SEQUENCE CSM_GROUP_GROUP_ID_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_PG_PE ( 
	PG_PE_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_PG_PE_PG_PE_ID_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

CREATE OR REPLACE TRIGGER SET_CSM_PG_PE_PG_PE_ID
BEFORE INSERT
ON CSM_PG_PE
FOR EACH ROW
BEGIN
  SELECT CSM_PG_PE_PG_PE_ID_SEQ.NEXTVAL
  INTO :NEW.PG_PE_ID
  FROM DUAL;
END;
/


CREATE OR REPLACE TRIGGER SET_CSM_PG_PE_UPDATE_DATE
BEFORE INSERT
ON CSM_PG_PE
FOR EACH ROW
BEGIN
  SELECT SYSDATE
  INTO :NEW.UPDATE_DATE
  FROM DUAL;
END;
/


CREATE TABLE CSM_PRIVILEGE ( 
	PRIVILEGE_ID NUMBER(38) NOT NULL,
	PRIVILEGE_NAME VARCHAR2(100) NOT NULL,
	PRIVILEGE_DESCRIPTION VARCHAR2(200),
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_PRIVILEGE_PRIVILEGE_ID_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_PROTECTION_ELEMENT ( 
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_NAME VARCHAR2(100) NOT NULL,
	PROTECTION_ELEMENT_DESCRIPTION VARCHAR2(200),
	OBJECT_ID VARCHAR2(100) NOT NULL,
	ATTRIBUTE VARCHAR2(100),
	PROTECTION_ELEMENT_TYPE VARCHAR2(100),
	APPLICATION_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_PROTECTIO_PROTECTION_E_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_PROTECTION_GROUP ( 
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_NAME VARCHAR2(100) NOT NULL,
	PROTECTION_GROUP_DESCRIPTION VARCHAR2(200),
	APPLICATION_ID NUMBER(38) NOT NULL,
	LARGE_ELEMENT_COUNT_FLAG NUMBER(1) NOT NULL,
	UPDATE_DATE DATE NOT NULL,
	PARENT_PROTECTION_GROUP_ID NUMBER(38)
) 
;
CREATE SEQUENCE CSM_PROTECTIO_PROTECTION_G_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_ROLE ( 
	ROLE_ID NUMBER(38) NOT NULL,
	ROLE_NAME VARCHAR2(100) NOT NULL,
	ROLE_DESCRIPTION VARCHAR2(200),
	APPLICATION_ID NUMBER(38) NOT NULL,
	ACTIVE_FLAG NUMBER(1) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_ROLE_ROLE_ID_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_ROLE_PRIVILEGE ( 
	ROLE_PRIVILEGE_ID NUMBER(38) NOT NULL,
	ROLE_ID NUMBER(38) NOT NULL,
	PRIVILEGE_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

CREATE OR REPLACE TRIGGER SET_CSM_ROLE_PRIV_ROLE_PRIVILE
BEFORE INSERT
ON CSM_ROLE_PRIVILEGE
FOR EACH ROW
BEGIN
  SELECT CSM_ROLE_PRIV_ROLE_PRIVILE_SEQ.NEXTVAL
  INTO :NEW.ROLE_PRIVILEGE_ID
  FROM DUAL;
END;
/


CREATE OR REPLACE TRIGGER SET_CSM_ROLE_PRIV_UPDATE_DATE
BEFORE INSERT
ON CSM_ROLE_PRIVILEGE
FOR EACH ROW
BEGIN
  SELECT SYSDATE
  INTO :NEW.UPDATE_DATE
  FROM DUAL;
END;
/


CREATE TABLE CSM_USER ( 
	USER_ID NUMBER(38) NOT NULL,
	LOGIN_NAME VARCHAR2(100) NOT NULL,
	FIRST_NAME VARCHAR2(100) NOT NULL,
	LAST_NAME VARCHAR2(100) NOT NULL,
	ORGANIZATION VARCHAR2(100),
	DEPARTMENT VARCHAR2(100),
	TITLE VARCHAR2(100),
	PHONE_NUMBER VARCHAR2(15),
	PASSWORD VARCHAR2(100),
	EMAIL_ID VARCHAR2(100),
	START_DATE DATE,
	END_DATE DATE,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_USER_USER_ID_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_USER_GROUP ( 
	USER_GROUP_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38) NOT NULL,
	GROUP_ID NUMBER(38) NOT NULL
) 
;
CREATE SEQUENCE CSM_USER_GROU_USER_GROUP_I_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

CREATE OR REPLACE TRIGGER SET_CSM_USER_GROU_USER_GROUP_I
BEFORE INSERT
ON CSM_USER_GROUP
FOR EACH ROW
BEGIN
  SELECT CSM_USER_GROU_USER_GROUP_I_SEQ.NEXTVAL
  INTO :NEW.USER_GROUP_ID
  FROM DUAL;
END;
/


CREATE TABLE CSM_USER_GROUP_ROLE_PG ( 
	USER_GROUP_ROLE_PG_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38),
	GROUP_ID NUMBER(38),
	ROLE_ID NUMBER(38) NOT NULL,
	PROTECTION_GROUP_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_USER_GROU_USER_GROUP_R_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;


CREATE TABLE CSM_USER_PE ( 
	USER_PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	PROTECTION_ELEMENT_ID NUMBER(38) NOT NULL,
	USER_ID NUMBER(38) NOT NULL,
	UPDATE_DATE DATE NOT NULL
) 
;
CREATE SEQUENCE CSM_USER_PE_USER_PROTECTIO_SEQ
increment by 1
start with 1
NOMAXVALUE
minvalue 1
nocycle
nocache
noorder;

CREATE OR REPLACE TRIGGER SET_CSM_USER_PE_USER_PROTECTIO
BEFORE INSERT
ON CSM_USER_PE
FOR EACH ROW
BEGIN
  SELECT CSM_USER_PE_USER_PROTECTIO_SEQ.NEXTVAL
  INTO :NEW.USER_PROTECTION_ELEMENT_ID
  FROM DUAL;
END;
/


CREATE OR REPLACE TRIGGER SET_CSM_USER_PE_UPDATE_DATE
BEFORE INSERT
ON CSM_USER_PE
FOR EACH ROW
BEGIN
  SELECT SYSDATE
  INTO :NEW.UPDATE_DATE
  FROM DUAL;
END;
/


ALTER TABLE CSM_APPLICATION ADD CONSTRAINT PK_APPLICATION 
PRIMARY KEY (APPLICATION_ID) 
;

ALTER TABLE CSM_GROUP ADD CONSTRAINT PK_GROUP 
PRIMARY KEY (GROUP_ID) 
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT PK_PG_PE 
PRIMARY KEY (PG_PE_ID) 
;

ALTER TABLE CSM_PRIVILEGE ADD CONSTRAINT PK_PRIVILEGE 
PRIMARY KEY (PRIVILEGE_ID) 
;

ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT PK_PROTECTION_ELEMENT 
PRIMARY KEY (PROTECTION_ELEMENT_ID) 
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT PK_PROTECTION_GROUP 
PRIMARY KEY (PROTECTION_GROUP_ID) 
;

ALTER TABLE CSM_ROLE ADD CONSTRAINT PK_ROLE 
PRIMARY KEY (ROLE_ID) 
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT PK_ROLE_PRIVILEGE 
PRIMARY KEY (ROLE_PRIVILEGE_ID) 
;

ALTER TABLE CSM_USER ADD CONSTRAINT PK_USER 
PRIMARY KEY (USER_ID) 
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT PK_USER_GROUP 
PRIMARY KEY (USER_GROUP_ID) 
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT PK_USER_GROUP_ROLE_PG 
PRIMARY KEY (USER_GROUP_ROLE_PG_ID) 
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT PK_USER_PROTECTION_ELEMENT 
PRIMARY KEY (USER_PROTECTION_ELEMENT_ID) 
;


ALTER TABLE CSM_APPLICATION
ADD CONSTRAINT UQ_APPLICATION_NAME UNIQUE (APPLICATION_NAME)
;
ALTER TABLE CSM_GROUP
ADD CONSTRAINT UQ_GROUP_GROUP_NAME UNIQUE (APPLICATION_ID, GROUP_NAME)
;
ALTER TABLE CSM_PG_PE
ADD CONSTRAINT UQ_PG_PE_PG_PE_ID UNIQUE (PROTECTION_ELEMENT_ID, PROTECTION_GROUP_ID)
;
ALTER TABLE CSM_PRIVILEGE
ADD CONSTRAINT UQ_PRIVILEGE_NAME UNIQUE (PRIVILEGE_NAME)
;
ALTER TABLE CSM_PROTECTION_ELEMENT
ADD CONSTRAINT UQ_PE_OBJ_ATT_APP_ID UNIQUE (OBJECT_ID, ATTRIBUTE, APPLICATION_ID)
;
ALTER TABLE CSM_PROTECTION_GROUP
ADD CONSTRAINT UQ_PG_PG_NAME UNIQUE (APPLICATION_ID, PROTECTION_GROUP_NAME)
;
ALTER TABLE CSM_ROLE
ADD CONSTRAINT UQ_ROLE_ROLE_NAME UNIQUE (APPLICATION_ID, ROLE_NAME)
;
ALTER TABLE CSM_ROLE_PRIVILEGE
ADD CONSTRAINT UQ_ROLE_ID_PRIVILEGE_ID UNIQUE (PRIVILEGE_ID, ROLE_ID)
;
ALTER TABLE CSM_USER
ADD CONSTRAINT UQ_LOGIN_NAME UNIQUE (LOGIN_NAME)
;
ALTER TABLE CSM_USER_PE
ADD CONSTRAINT UQ_USER_PE_PE_ID UNIQUE (USER_ID, PROTECTION_ELEMENT_ID)
;


ALTER TABLE CSM_GROUP ADD CONSTRAINT FK_CSM_GROUP_CSM_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PG_PE_PE 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PG_PE_PG 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT FK_PE_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_PG 
FOREIGN KEY (PARENT_PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
;

ALTER TABLE CSM_ROLE ADD CONSTRAINT FK_ROLE_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE_PRIVILEGE_PRIVILEGE 
FOREIGN KEY (PRIVILEGE_ID) REFERENCES CSM_PRIVILEGE (PRIVILEGE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE_PRIVILEGE_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_PG 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PG_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_USER_PE_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_USER_PE_PE 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;

COMMIT;
