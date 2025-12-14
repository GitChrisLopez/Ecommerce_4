
# Autores
INSERT INTO autores (id_autor, nombre, apellido_paterno, apellido_materno) VALUES 
(1, 'Antoine', 'de Saint-Exupéry', NULL),
(2, 'Lewis', 'Carroll', NULL),
(3, 'Miguel', 'de Cervantes', 'Saavedra'),
(4, 'Jane', 'Austen', NULL),
(5, 'George', 'Orwell', NULL);

# Editoriales
INSERT INTO editoriales (id_editorial, nombre) VALUES 
(1, 'Editorial Porrúa'),
(2, 'Pengüin Random House'),
(3, 'Alianza Editorial'),
(4, 'Editorial Juventud'),
(5, 'Austral');

# Categorías
INSERT INTO categorias (id_categoria, nombre) VALUES 
(1, 'Infantil'),
(2, 'Fantasía'),
(3, 'Clásicos'),
(4, 'Romance'),
(5, 'Ciencia Ficción'),
(6, 'Filosofía');

# Libros
INSERT INTO libros (id_libro, titulo, sinopsis, fecha_publicacion, id_autor, id_editorial) VALUES 
(1, 'El Principito', 'Un piloto perdido en el desierto encuentra a un pequeño príncipe de otro planeta.', '1943-04-06', 1, 4),
(2, 'Alicia en el país de las maravillas', 'Una niña cae por una madriguera de conejo hacia un mundo de fantasía.', '1865-11-26', 2, 3),
(3, 'Don Quijote de la Mancha', 'Las aventuras de un hidalgo que enloquece leyendo libros de caballerías.', '1605-01-16', 3, 1),
(4, 'Orgullo y Prejuicio', 'La turbulenta relación entre Elizabeth Bennet y el señor Darcy.', '1813-01-28', 4, 2),
(5, '1984', 'Una novela distópica sobre un régimen totalitario y el Gran Hermano.', '1949-06-08', 5, 5);

# Categorías de Libros
INSERT INTO libro_categoria (id_libro, id_categoria) VALUES 
(1, 1), (1, 6),
(2, 1), (2, 2),
(3, 3),
(4, 3), (4, 4),
(5, 3), (5, 5);


# Productos de El Principito.
INSERT INTO productos (id_producto, isbn, no_paginas, precio, stock, url_imagen, formato, id_libro) VALUES 
(1, '9788498381498', 96, 150.00, 50, 'imgs/principito-blanda.jpg', 'TAPA_BLANDA', 1),
(2, '9780156013987', 96, 80.00, 1000, 'imgs/principito-ebook.jpg', 'E_BOOK', 1);

# Productos de Alicia en el país de las maravillas:
INSERT INTO productos (id_producto, isbn, no_paginas, precio, stock, url_imagen, formato, id_libro) VALUES 
(3, '9788420674209', 320, 280.50, 30, 'imgs/alicia-dura.jpg', 'TAPA_DURA', 2),
(4, '9788420674216', 320, 95.00, 1000, 'imgs/alicia-ebook.jpg', 'E_BOOK', 2);

# Productos de Don Quijote de la Mancha
INSERT INTO productos (id_producto, isbn, no_paginas, precio, stock, url_imagen, formato, id_libro) VALUES 
(5, '9788424117900', 1424, 120.00, 100, 'imgs/quijote-bolsillo.jpg', 'BOLSILLO', 3),
(6, '9788430600564', 1424, 850.00, 5, 'imgs/quijote-dura.jpg', 'TAPA_DURA', 3);

# Productos de Don Quijote de la Mancha
INSERT INTO productos (id_producto, isbn, no_paginas, precio, stock, url_imagen, formato, id_libro) VALUES 
(7, '9788497940825', 448, 210.00, 45, 'imgs/orgullo-blanda.jpg', 'TAPA_BLANDA', 4);

# Productos de 1984
INSERT INTO productos (id_producto, isbn, no_paginas, precio, stock, url_imagen, formato, id_libro) VALUES 
(8, '9780141036144', 328, 190.00, 60, 'imgs/1984-bolsillo.jpg', 'BOLSILLO', 5),
(9, '9780451524935', 328, 95.00, 500, 'imgs/1984-ebook.jpg', 'E_BOOK', 5);


# Usuarios
INSERT INTO usuarios (id_usuario, nombre, apellido_paterno, apellido_materno, correo, contrasenia, tipo_usuario) VALUES 
(1, 'Juan', 'Pérez', 'García', 'juan.admin@libreria.com', 'admin123', 'ADMINISTRADOR'),
(2, 'María', 'López', 'Sánchez', 'maria.lopez@gmail.com', 'cliente123', 'CLIENTE'),
(3, 'Carlos', 'Ruiz', 'Zafón', 'carlos.ruiz@hotmail.com', 'cliente456', 'CLIENTE');

# Administradores
INSERT INTO administradores (id_usuario) VALUES (1);

# Clientes
INSERT INTO clientes (id_usuario, telefono, url_imagen_perfil, activo, id_carrito) VALUES 
(2, '5511223344', 'imgs/perfil_maria.jpg', 1, NULL),
(3, '6699887766', NULL, 1, NULL);


# Carritos de compra
INSERT INTO carritos (id_carrito, total, id_cliente) VALUES 
(1, 0.00, 2),
(2, 0.00, 3);

# Carritos de compra de Clientes
UPDATE clientes SET id_carrito = 1 WHERE id_usuario = 2;
UPDATE clientes SET id_carrito = 2 WHERE id_usuario = 3;

# Direcciones de Clientes
INSERT INTO direcciones (id_direccion, calle, colonia, codigo_postal, numero, id_cliente) VALUES 
(1, 'Calle de las Rosas', 'Jardines', '85000', '10', 2),
(2, 'Av. Tecnológico', 'Centro', '85100', '500', 3);


# Pagos
INSERT INTO metodo_pago (id_metodo_pago, fecha_realizacion, tipo_pago) VALUES 
(1, '2023-10-15', 'TARJETA'),
(2, '2023-11-20', 'CONTRA_ENTREGA'),
(3, '2023-11-25', 'TRANSFERENCIA');

# Pagos con tarjeta
INSERT INTO pago_tarjeta (id_metodo_pago, numero, fecha_vencimiento, nombre_titular, cvv) 
VALUES (1, 41523134, '2026-08-01', 'MARIA LOPEZ', '999');

# Pago contra entrega
INSERT INTO pago_contraentrega (id_metodo_pago) VALUES (2);

# Pago por transferencia
INSERT INTO pago_transferencia (id_metodo_pago, banco_emisor, cuatro_digitos) 
VALUES (3, 'BBVA', '5678');

# Pedidos
INSERT INTO pedidos (id_pedido, numeroUnico_pedido, fecha_pedido, estado_pedido, total, id_cliente, id_direccion, id_metodoPago) VALUES 
(1, 'PED-001-2023', '2023-10-15 14:00:00', 'ENTREGADO', 300.00, 2, 1, 1),
(2, 'PED-002-2023', '2023-11-20 09:30:00', 'ENVIADO', 280.50, 3, 2, 2);

# Productos en pedidos
INSERT INTO productos_pedido (id_producto_pedido, cantidad, precio_unitario, id_pedido, id_producto) VALUES 
(1, 2, 150.00, 1, 1);

INSERT INTO productos_pedido (id_producto_pedido, cantidad, precio_unitario, id_pedido, id_producto) VALUES 
(2, 1, 280.50, 2, 3);

# Productos en carrito
INSERT INTO productos_carrito (id_producto_carrito, cantidad, precio_unitario, id_carrito, id_producto) VALUES 
(1, 1, 120.00, 1, 5);

# Actualización de carrito
UPDATE carritos SET total = 120.00 WHERE id_carrito = 1;