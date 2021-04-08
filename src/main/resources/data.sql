INSERT INTO perfil (perfil) VALUES ('ROLE_USUARIO');


INSERT INTO usuario (email,senha,created_At) VALUES ('pedro@email.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG',NOW());
INSERT INTO usuarios_perfis (usuario_id,perfis_id) VALUES (1,1);
INSERT INTO categoria (nome) VALUES ('Eletrônicos');


