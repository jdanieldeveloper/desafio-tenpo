-------------------------------------------------------------------------------------------------------------------
--                                                TABLES
-------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE OPERATIONS
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE OPERATIONS (
            OPERATION_ID INTEGER NOT NULL,
            NAME CHARACTER VARYING(255),
            TYPE CHARACTER VARYING(25),
            DESCRIPCION CHARACTER VARYING(255),
            VALUE CHARACTER VARYING(255),
            RESULT INTEGER DEFAULT 0,
            CREATED_BY CHARACTER VARYING(50),
            CREATED_DATE TIMESTAMP WITHOUT TIME ZONE,
            DETAILS CHARACTER VARYING(255),
  CONSTRAINT OPERATION_ID_PKEY PRIMARY KEY (OPERATION_ID));

  CREATE SEQUENCE OPERATION_ID_SEQ START WITH 1 INCREMENT BY 1;
-------------------------------------------------------------------------------------------------------------------

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE USER_LOGIN
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE USER_LOGIN (
  				USER_LOGIN_ID VARCHAR(20) NOT NULL,
                CURRENT_PASSWORD VARCHAR(255),
                PASSWORD_HINT VARCHAR(255),
                IS_SYSTEM CHAR(1),
                ENABLED CHAR(1),
                HAS_LOGGED_OUT CHAR(1),
                REQUIRE_PASSWORD_CHANGE CHAR(1),
                LAST_CURRENCY_UOM VARCHAR(20),
                LAST_LOCALE VARCHAR(10),
                LAST_TIME_ZONE VARCHAR(60),
                DISABLED_DATE_TIME TIMESTAMP,
                SUCCESSIVE_FAILED_LOGINS NUMERIC(20,0),
                EXTERNAL_AUTH_ID VARCHAR(255),
                USER_LDAP_DN VARCHAR(255),
                DISABLED_BY VARCHAR(255),
                PARTY_ID BIGINT,
  CONSTRAINT USER_LOGIN_PK PRIMARY KEY (PARTY_ID));

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE PARTY_TYPE
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE PARTY_TYPE (
  				PARTY_TYPE_ID VARCHAR(20) NOT NULL,
				PARENT_TYPE_ID VARCHAR(20),
				DESCRIPTION VARCHAR(255),
  CONSTRAINT PARTY_TYPE_PK PRIMARY KEY (PARTY_TYPE_ID),
  CONSTRAINT PARTY_TYPE_PAR FOREIGN KEY (PARENT_TYPE_ID)
			REFERENCES PARTY_TYPE (PARTY_TYPE_ID));

-------------------------------------------------------------------------------------------------------------------
-- SCHEMA FOR TABLE PARTY
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE  PARTY (
				PARTY_ID BIGINT NOT NULL,
				PARTY_TYPE_ID VARCHAR(20),
				EXTERNAL_ID VARCHAR(20),
				DESCRIPTION VARCHAR(255),
				STATUS_ID VARCHAR(20) NOT NULL,
				CREATED_DATE TIMESTAMP NOT NULL,
				CREATED_BY_USER_LOGIN VARCHAR(255) NOT NULL,
				LAST_MODIFIED_DATE TIMESTAMP,
				LAST_MODIFIED_BY_USER_LOGIN VARCHAR(255),
	CONSTRAINT PARTY_PK PRIMARY KEY ("PARTY_ID"),
	CONSTRAINT USER_LOGIN_PARTY_FK FOREIGN KEY (PARTY_ID)
			   REFERENCES USER_LOGIN(PARTY_ID),
	CONSTRAINT PARTY_PTY_TYP_FK FOREIGN KEY (PARTY_TYPE_ID)
			REFERENCES PARTY_TYPE (PARTY_TYPE_ID));
   --
   -- COMMENT ON COLUMN PARTY.PAATTY_ID IS 'Party identifier';

-------------------------------------------------------------------------------------------------------------------
--  SECUENCE FOR TABLE PARTY
-------------------------------------------------------------------------------------------------------------------
CREATE SEQUENCE PARTY_ID_SEQ;

-----------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE ROLE_TYPE
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE ROLE_TYPE (
  				ROLE_TYPE_ID VARCHAR(20) NOT NULL,
				PARENT_TYPE_ID VARCHAR(20),
				DESCRIPTION VARCHAR(255),
  CONSTRAINT ROLE_TYPE_PK PRIMARY KEY (ROLE_TYPE_ID),
  CONSTRAINT ROLE_TYPE_PAR FOREIGN KEY (PARENT_TYPE_ID)
			REFERENCES ROLE_TYPE (ROLE_TYPE_ID));

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE PARTY_ROLE
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE PARTY_ROLE (
  				PARTY_ID BIGINT NOT NULL,
				ROLE_TYPE_ID VARCHAR(20) NOT NULL,
				DESCRIPTION VARCHAR(255),
  CONSTRAINT PARTY_ROLE_PK PRIMARY KEY (PARTY_ID, ROLE_TYPE_ID));
