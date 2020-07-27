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