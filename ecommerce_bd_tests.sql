DROP DATABASE IF EXISTS ecommerce_bd_tests;
-- Primero crear la BD
CREATE DATABASE ecommerce_bd_tests;
-- Correr el main de Persistencia
-- Despues ejecutar esta linea
USE ecommerce_bd_tests;

-- finalmente se crean las tablas 
-- admin hereda de usuario asi que hacemos el usuario primero
INSERT INTO usuarios (nombre, apellido_paterno, correo, contrasenia, tipo_usuario)
VALUES ("Admin", "Root", "admin@correo.com", "admin123", "ADMINISTRADOR");

INSERT INTO usuarios (nombre, apellido_paterno, correo, contrasenia, tipo_usuario)
VALUES ("user", "usuario", "user@gmail.com", "12345", "USUARIO");

SELECT * FROM usuarios;

-- luego admin
INSERT INTO administradores (id_usuario)
SELECT id_usuario FROM usuarios WHERE correo = "admin@correo.com";


-- editorial


INSERT INTO editoriales (nombre)
values("Editorial Patito");


-- autor
insert into autor(nombre, apellido_materno,apellido_paterno)
values ("Jean" ,"de" "Saint-Exupéry");
