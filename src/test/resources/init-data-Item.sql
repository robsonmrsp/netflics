 ALTER SEQUENCE hibernate_sequence RESTART WITH 10001;

 TRUNCATE TABLE tenant CASCADE;
 TRUNCATE TABLE app_user CASCADE;
 TRUNCATE TABLE role CASCADE;
 TRUNCATE TABLE user_role CASCADE;
 TRUNCATE TABLE revinfo CASCADE;

 INSERT INTO tenant (id, cnpj, corporate_name,  phone_number, logo, name) values (101,'','JSetup Developer', '997608620','','JSetup Developer');

 INSERT INTO role( id, authority, description)  VALUES (101, 'ROLE_USER', 'Usuário do sistema');
 INSERT INTO app_user( id, id_tenant, enable, image, name, password, username, email) VALUES (101, 101, true, '', 'Usuário JSetup Comum', '$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2', 'jsetup', 'contato@jsetup.com');
 INSERT INTO user_role(id_role, id_user) values (101, 101);

TRUNCATE TABLE ITEM CASCADE;
INSERT INTO ITEM 	( id, id_tenant
			,NAME
			,TYPE
			,IDENTIFIER
			,DESCRIPTION
			)values(1, 101
			, 'name item1'
			, 'itemType item1'
			, 'identifier item1'
			, 'description item1'
			);
INSERT INTO ITEM 	( id, id_tenant
			,NAME
			,TYPE
			,IDENTIFIER
			,DESCRIPTION
			)values(2, 101
			, 'name item2'
			, 'itemType item2'
			, 'identifier item2'
			, 'description item2'
			);
INSERT INTO ITEM 	( id, id_tenant
			,NAME
			,TYPE
			,IDENTIFIER
			,DESCRIPTION
			)values(3, 101
			, 'name item3'
			, 'itemType item3'
			, 'identifier item3'
			, 'description item3'
			);
INSERT INTO ITEM 	( id, id_tenant
			,NAME
			,TYPE
			,IDENTIFIER
			,DESCRIPTION
			)values(4, 101
			, 'name item4'
			, 'itemType item4'
			, 'identifier item4'
			, 'description item4'
			);
INSERT INTO ITEM 	( id, id_tenant
			,NAME
			,TYPE
			,IDENTIFIER
			,DESCRIPTION
			)values(5, 101
			, 'name item5'
			, 'itemType item5'
			, 'identifier item5'
			, 'description item5'
			);
