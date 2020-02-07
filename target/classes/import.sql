/* Populate tables */
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');  
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', ''); 
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
INSERT INTO clients (name, last_name, email, create_at, pic) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');

/* Populate tabla products */
INSERT INTO products (name, price, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO products (name, price, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas bills */
INSERT INTO bills (description, observation, client_id, create_at) VALUES('bill equipos de oficina', null, 1, NOW());
INSERT INTO bills_items (quantity, bills_items_id, product_id) VALUES(1, 1, 1);
INSERT INTO bills_items (quantity, bills_items_id, product_id) VALUES(2, 1, 4);
INSERT INTO bills_items (quantity, bills_items_id, product_id) VALUES(1, 1, 5);
INSERT INTO bills_items (quantity, bills_items_id, product_id) VALUES(1, 1, 7);

INSERT INTO bills (description, observation, client_id, create_at) VALUES('bill Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO bills_items (quantity, bills_items_id, product_id) VALUES(3, 2, 6);