 ALTER SEQUENCE hibernate_sequence RESTART WITH 10001;

 TRUNCATE TABLE tenant CASCADE;
 TRUNCATE TABLE app_user CASCADE;
 TRUNCATE TABLE role CASCADE;
 TRUNCATE TABLE user_role CASCADE;

 INSERT INTO tenant (id, cnpj, corporate_name,  phone_number, logo, name) values (101,'','JSetup Developer', '997608620','','JSetup Developer');

 INSERT INTO role( id, id_tenant, authority, description)  VALUES (101, 101, 'ROLE_USER', 'Usuário do sistema');
 INSERT INTO app_user( id, id_tenant, enable, image, name, password, username, email) VALUES (101, 101, true, '', 'Usuário JSetup Comum', '$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2', 'jsetup', 'contato@jsetup.com');
 INSERT INTO user_role(id_role, id_user) values (101, 101);

TRUNCATE TABLE PERMISSION CASCADE;
INSERT INTO PERMISSION 	( id, id_tenant
			,NAME
			,DESCRIPTION
			,OPERATION
			,TAG_REMINDER
			)values(1, 101
			, 'name permission1'
			, 'description permission1'
			, 'operation permission1'
			, 'tagReminder permission1'
			);
INSERT INTO PERMISSION 	( id, id_tenant
			,NAME
			,DESCRIPTION
			,OPERATION
			,TAG_REMINDER
			)values(2, 101
			, 'name permission2'
			, 'description permission2'
			, 'operation permission2'
			, 'tagReminder permission2'
			);
INSERT INTO PERMISSION 	( id, id_tenant
			,NAME
			,DESCRIPTION
			,OPERATION
			,TAG_REMINDER
			)values(3, 101
			, 'name permission3'
			, 'description permission3'
			, 'operation permission3'
			, 'tagReminder permission3'
			);
INSERT INTO PERMISSION 	( id, id_tenant
			,NAME
			,DESCRIPTION
			,OPERATION
			,TAG_REMINDER
			)values(4, 101
			, 'name permission4'
			, 'description permission4'
			, 'operation permission4'
			, 'tagReminder permission4'
			);
INSERT INTO PERMISSION 	( id, id_tenant
			,NAME
			,DESCRIPTION
			,OPERATION
			,TAG_REMINDER
			)values(5, 101
			, 'name permission5'
			, 'description permission5'
			, 'operation permission5'
			, 'tagReminder permission5'
			);
