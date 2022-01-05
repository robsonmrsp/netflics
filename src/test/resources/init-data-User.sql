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

TRUNCATE TABLE APP_USER CASCADE;
INSERT INTO APP_USER 	( id, id_tenant
			,NAME
			,USERNAME
			,EMAIL
			,PASSWORD
			,IMAGE
			)values(1, 101
			, 'name user1'
			, 'username user1'
			, 'email user1'
			, 'password user1'
			, 'image user1'
			);
INSERT INTO APP_USER 	( id, id_tenant
			,NAME
			,USERNAME
			,EMAIL
			,PASSWORD
			,IMAGE
			)values(2, 101
			, 'name user2'
			, 'username user2'
			, 'email user2'
			, 'password user2'
			, 'image user2'
			);
INSERT INTO APP_USER 	( id, id_tenant
			,NAME
			,USERNAME
			,EMAIL
			,PASSWORD
			,IMAGE
			)values(3, 101
			, 'name user3'
			, 'username user3'
			, 'email user3'
			, 'password user3'
			, 'image user3'
			);
INSERT INTO APP_USER 	( id, id_tenant
			,NAME
			,USERNAME
			,EMAIL
			,PASSWORD
			,IMAGE
			)values(4, 101
			, 'name user4'
			, 'username user4'
			, 'email user4'
			, 'password user4'
			, 'image user4'
			);
INSERT INTO APP_USER 	( id, id_tenant
			,NAME
			,USERNAME
			,EMAIL
			,PASSWORD
			,IMAGE
			)values(5, 101
			, 'name user5'
			, 'username user5'
			, 'email user5'
			, 'password user5'
			, 'image user5'
			);
