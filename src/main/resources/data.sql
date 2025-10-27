-- INSERTS

-- SUPPLIERS
INSERT INTO supplier (company_name, ruc, phone, address, email, state, register_date) VALUES
('Textiles Andinos S.A.', '10456789123', '987654321', 'Av. Los Incas 123', 'contacto@andinos.com', 'A', TO_DATE('28/04/2025 09:00','DD/MM/YYYY HH24:MI'));
INSERT INTO supplier (company_name, ruc, phone, address, email, state, register_date) VALUES
('Distribuciones Lima SAC','10765432109','912345678','Jr. Lima 456','ventas@lima.com','I',TO_DATE('29/04/2025 14:30','DD/MM/YYYY HH24:MI'));
INSERT INTO supplier (company_name, ruc, phone, address, email, state, register_date) VALUES
('Moda Perú SAC','10987654321','911223344','Av. Grau 456','modaperu@correo.com','A',TO_DATE('30/04/2025 10:00','DD/MM/YYYY HH24:MI'));
INSERT INTO supplier (company_name, ruc, phone, address, email, state, register_date) VALUES
('Ropa y Estilo EIRL','10567891234','987321654','Calle Comercio 789','estilo@ropa.com','A',TO_DATE('01/05/2025 11:15','DD/MM/YYYY HH24:MI'));
INSERT INTO supplier (company_name, ruc, phone, address, email, state, register_date) VALUES
('Insumos Textiles S.A.C.','10876543210','922334455','Jr. Amazonas 321','insumos@textiles.com','A',TO_DATE('02/05/2025 15:45','DD/MM/YYYY HH24:MI'));
COMMIT;

-- EMPLOYEES
INSERT INTO employee (name,last_name,dni,phone,email) VALUES
('Carlos','Ramírez','75894621','987112233',NULL);
INSERT INTO employee (name,last_name,dni,phone,email) VALUES
('Luisa','Fernández','82457963','912334455',NULL);
INSERT INTO employee (name,last_name,dni,phone,email) VALUES
('Miguel','Reyes','74851236','900112233',NULL);
INSERT INTO employee (name,last_name,dni,phone,email) VALUES
('Diana','Ponce','74125698','911122233',NULL);
INSERT INTO employee (name,last_name,dni,phone,email) VALUES
('Andrea','Torres','73092145','923456789',NULL);
COMMIT;

-- CUSTOMERS
INSERT INTO customer (name,last_name,dni,phone,address,email,client_type,state) VALUES
('María','Gonzales','45678912','987000111','Av. Siempre Viva 742','maria@gmail.com','N','A');
INSERT INTO customer (name,last_name,dni,phone,address,email,client_type,state) VALUES
('José','Pérez','78945612','912345000','Calle Luna 123','jose@hotmail.com','F','I');
INSERT INTO customer (name,last_name,dni,phone,address,email,client_type,state) VALUES
('Lucía','Martínez','65432198','987123456','Av. El Sol 100','lucia@gmail.com','F','A');
INSERT INTO customer (name,last_name,dni,phone,address,email,client_type,state) VALUES
('Pedro','Ríos','87451236','911234567','Calle Central 222','pedro@outlook.com','N','A');
INSERT INTO customer (name,last_name,dni,phone,address,email,client_type,state) VALUES
('Andrea','Quispe','80123456','920334455','Jr. La Marina 345','andrea@yahoo.com','F','A');
COMMIT;

-- BUYS
INSERT INTO buys (purchase_date,total,purchase_status,supplier_identifier) VALUES
(TO_DATE('01/05/2025 08:30','DD/MM/YYYY HH24:MI'),550.75,'R',1);
INSERT INTO buys (purchase_date,total,purchase_status,supplier_identifier) VALUES
(TO_DATE('02/05/2025 09:45','DD/MM/YYYY HH24:MI'),320.40,'P',2);
INSERT INTO buys (purchase_date,total,purchase_status,supplier_identifier) VALUES
(TO_DATE('03/05/2025 10:00','DD/MM/YYYY HH24:MI'),150.00,'R',3);
INSERT INTO buys (purchase_date,total,purchase_status,supplier_identifier) VALUES
(TO_DATE('04/05/2025 11:30','DD/MM/YYYY HH24:MI'),430.90,'P',4);
INSERT INTO buys (purchase_date,total,purchase_status,supplier_identifier) VALUES
(TO_DATE('05/05/2025 12:15','DD/MM/YYYY HH24:MI'),610.20,'R',5);
COMMIT;

-- GARMENTS
INSERT INTO garment (name,garment_size,color,unit_price,stock,state,register_date) VALUES
('Camisa Blanca','M','Blanco',29.99,12,'A',TO_DATE('03/05/2025 10:15','DD/MM/YYYY HH24:MI'));
INSERT INTO garment (name,garment_size,color,unit_price,stock,state,register_date) VALUES
('Pantalón Negro','L','Negro',45.50,7,'I',TO_DATE('03/05/2025 11:40','DD/MM/YYYY HH24:MI'));
INSERT INTO garment (name,garment_size,color,unit_price,stock,state,register_date) VALUES
('Polera Azul','S','Azul',35.00,10,'A',TO_DATE('04/05/2025 09:00','DD/MM/YYYY HH24:MI'));
INSERT INTO garment (name,garment_size,color,unit_price,stock,state,register_date) VALUES
('Falda Roja','M','Rojo',27.80,6,'A',TO_DATE('05/05/2025 10:30','DD/MM/YYYY HH24:MI'));
INSERT INTO garment (name,garment_size,color,unit_price,stock,state,register_date) VALUES
('Vestido Verde','L','Verde',50.90,5,'A',TO_DATE('05/05/2025 13:45','DD/MM/YYYY HH24:MI'));
COMMIT;

-- DETAIL PURCHASES
INSERT INTO detailPurchase (amount,unit_price,subtotal,buys_identifier,garment_identifier) VALUES
(5,110.15,550.75,1,1);
INSERT INTO detailPurchase (amount,unit_price,subtotal,buys_identifier,garment_identifier) VALUES
(4,80.10,320.40,2,2);
INSERT INTO detailPurchase (amount,unit_price,subtotal,buys_identifier,garment_identifier) VALUES
(3,50.00,150.00,3,3);
INSERT INTO detailPurchase (amount,unit_price,subtotal,buys_identifier,garment_identifier) VALUES
(6,71.82,430.90,4,4);
INSERT INTO detailPurchase (amount,unit_price,subtotal,buys_identifier,garment_identifier) VALUES
(7,87.17,610.20,5,5);
COMMIT;

-- SALES
INSERT INTO sale (sale_date,total,client_identifier,employee_identifier,sale_status) VALUES
(TO_DATE('03/05/2025 12:15','DD/MM/YYYY HH24:MI'),90.00,1,1,'A');
INSERT INTO sale (sale_date,total,client_identifier,employee_identifier,sale_status) VALUES
(TO_DATE('03/05/2025 13:20','DD/MM/YYYY HH24:MI'),45.50,2,2,'I');
INSERT INTO sale (sale_date,total,client_identifier,employee_identifier,sale_status) VALUES
(TO_DATE('04/05/2025 14:00','DD/MM/YYYY HH24:MI'),70.00,3,3,'A');
INSERT INTO sale (sale_date,total,client_identifier,employee_identifier,sale_status) VALUES
(TO_DATE('04/05/2025 15:15','DD/MM/YYYY HH24:MI'),120.00,4,4,'A');
INSERT INTO sale (sale_date,total,client_identifier,employee_identifier,sale_status) VALUES
(TO_DATE('05/05/2025 16:30','DD/MM/YYYY HH24:MI'),50.90,5,5,'I');
COMMIT;

-- DETAIL SALES
INSERT INTO detailSale (sale_identifier,product_identifier,quantity,unit_price) VALUES
(1,1,2,29.99);
INSERT INTO detailSale (sale_identifier,product_identifier,quantity,unit_price,) VALUES
(2,2,1,45.50);
INSERT INTO detailSale (sale_identifier,product_identifier,quantity,unit_price) VALUES
(3,3,2,35.00);
INSERT INTO detailSale (sale_identifier,product_identifier,quantity,unit_price) VALUES
(4,4,3,27.80);
INSERT INTO detailSale (sale_identifier,product_identifier,quantity,unit_price) VALUES
(5,5,1,50.90);
COMMIT;