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

TRUNCATE TABLE ACCESS_GROUP CASCADE;
INSERT INTO ACCESS_GROUP 	( id, id_tenant
			,NAME
			,DESCRIPTION
			)values(1, 101
			, 'name group1'
			, 'description group1'
			);
INSERT INTO ACCESS_GROUP 	( id, id_tenant
			,NAME
			,DESCRIPTION
			)values(2, 101
			, 'name group2'
			, 'description group2'
			);
INSERT INTO ACCESS_GROUP 	( id, id_tenant
			,NAME
			,DESCRIPTION
			)values(3, 101
			, 'name group3'
			, 'description group3'
			);
INSERT INTO ACCESS_GROUP 	( id, id_tenant
			,NAME
			,DESCRIPTION
			)values(4, 101
			, 'name group4'
			, 'description group4'
			);
INSERT INTO ACCESS_GROUP 	( id, id_tenant
			,NAME
			,DESCRIPTION
			)values(5, 101
			, 'name group5'
			, 'description group5'
			);
