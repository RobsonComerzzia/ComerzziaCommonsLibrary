set foreign_key_checks = 0;

lock tables grupo write, grupo_permissao write, permissao write,
			usuario write, usuario_grupo write,
			oauth2_registered_client write;

delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from usuario_grupo;
delete from oauth2_registered_client;

set foreign_key_checks = 1;

alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;

insert into permissao (id, client_id, nome, descricao) values (1, 'client_web', 'ROLE_CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões');
insert into permissao (id, client_id, nome, descricao) values (2, 'client_web', 'ROLE_EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários, grupos e permissões');

insert into grupo (id, client_id, nome) values (1, 'client_web', 'Administrador'), (2, 'client_web', 'Supervisor'), (3, 'client_web', 'Gerente'), (4, 'client_web', 'Vendedor');


# Adiciona todas as permissoes no grupo do administrador
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id from permissao;


insert into usuario (id, client_id, nome, email, senha, data_cadastro) values
(1, 'client-web', 'Admin', 'admin@seidor.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1);


INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'client-backend', '2024-11-25 12:00:00', '$2a$12$EXnBp8C3NVMKv5R/MFeiV.480/6ztpax2APdC/NF8As0IX8cyh3om', NULL, 'Client Backend', 'client_secret_basic', 'client_credentials', '', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('2', 'client-web', '2024-11-25 12:00:00', '$2a$12$wWy.VTXgXov/bIX/K6mRbuGSQj5FpFmyJuIMYNXkDti1vE4lG5E.G', NULL, 'Client Web', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html,http://127.0.0.1:8080/authorized', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('3', 'client-without-consent', '2024-11-25 12:00:00', '$2a$12$hQrYlkkWoH79iSO5aU17uOO98k5KaThKV7fJC1n1glgdBSb6yTDmC', NULL, 'Client Without Consent', 'client_secret_basic', 'authorization_code', 'http://www.seidor.local:8082', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');


unlock tables;
