-------------------------------------------------------------------------------------------------------------------
-- SEED DATA FOR TABLE OPERATIONS
-------------------------------------------------------------------------------------------------------------------
INSERT INTO OPERATIONS(OPERATION_ID, NAME, TYPE, DESCRIPCION, VALUE, RESULT, CREATED_BY, CREATED_DATE, DETAILS) VALUES(NEXTVAL('OPERATION_ID_SEQ'), 'Addition', 'ADD', 'addition operation', '123', 6, 'dcarvajal', '2020-07-08 18:21:36.542', '1 + 1 = 2');
INSERT INTO OPERATIONS(OPERATION_ID, NAME, TYPE, DESCRIPCION, VALUE, RESULT, CREATED_BY, CREATED_DATE, DETAILS) VALUES(NEXTVAL('OPERATION_ID_SEQ'), 'Subtraction', 'SUB', 'subtraction operation', '123', 6, 'dcarvajal', '2020-07-08 18:21:36.542', '1 - 1 = 0');
INSERT INTO OPERATIONS(OPERATION_ID, NAME, TYPE, DESCRIPCION, VALUE, RESULT, CREATED_BY, CREATED_DATE, DETAILS) VALUES(NEXTVAL('OPERATION_ID_SEQ'), 'Division', 'DIV', 'division operation', '123', 6, 'dcarvajal', '2020-07-08 18:21:36.542', '1 / 1 = 0');
INSERT INTO OPERATIONS(OPERATION_ID, NAME, TYPE, DESCRIPCION, VALUE, RESULT, CREATED_BY, CREATED_DATE, DETAILS) VALUES(NEXTVAL('OPERATION_ID_SEQ'), 'Multiplication', 'MUL', 'multiplication operation', '123', 6, 'dcarvajal', '2020-07-08 18:21:36.542', '1 * 1 = 1');

-------------------------------------------------------------------------------------------------------------------
-- SEED DATA FOR TABLE PARTY_TYPE
-------------------------------------------------------------------------------------------------------------------
INSERT INTO PARTY_TYPE(PARTY_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR', NULL, 'Default party type when to do some user');

-------------------------------------------------------------------------------------------------------------------
-- SEED DATA FOR TABLE ROLE_TYPE
-------------------------------------------------------------------------------------------------------------------
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('ADMIN', NULL, 'Role Administrator with all grant');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_READ', NULL, 'Role Visitor only read content');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_CREATE', NULL, 'Role Visitor only create content');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_UPDATE', NULL, 'Role Visitor only update content');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_DELETE', NULL, 'Role Visitor only delete content');

-------------------------------------------------------------------------------------------------------------------
-- SEED DATA FOR TABLE USER LOGIN AND RELATIONSHIPS
-------------------------------------------------------------------------------------------------------------------
INSERT INTO USER_LOGIN (USER_LOGIN_ID, ENABLED, CURRENT_PASSWORD, PARTY_ID) VALUES ('dcarvajals', 'T', '1234678', 1);
INSERT INTO PARTY (PARTY_ID, PARTY_TYPE_ID, DESCRIPTION, STATUS_ID, CREATED_DATE, CREATED_BY_USER_LOGIN, LAST_MODIFIED_DATE, LAST_MODIFIED_BY_USER_LOGIN) VALUES (1, 'VISITOR', null, 'ENABLED', '2020-08-14 16:25:28.27', 'dcarvajals', null, null);
INSERT INTO PARTY_ROLE (PARTY_ID, ROLE_TYPE_ID, DESCRIPTION) VALUES (1, 'VISITOR_READ', 'Role Visitor only read content');
INSERT INTO PARTY_ROLE (PARTY_ID, ROLE_TYPE_ID, DESCRIPTION) VALUES (1, 'VISITOR_CREATE', 'Role Visitor only create content');



