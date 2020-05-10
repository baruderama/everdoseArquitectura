CREATE TABLE Drugstore ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), address VARCHAR(50), phone VARCHAR(50), email VARCHAR(20), uri VARCHAR(100) );
CREATE TABLE ProductFromDrugstore ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), description VARCHAR(100), price FLOAT, keywords VARCHAR(40), drugstore_id INT UNSIGNED );
CREATE TABLE Product ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), description VARCHAR(100), location VARCHAR(10), price FLOAT, threshold INT, amount INT, image VARCHAR(200), keywords VARCHAR(40) );
CREATE TABLE Supplier ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), address VARCHAR(50), phone VARCHAR(15), email VARCHAR(20), uri VARCHAR(100) );
CREATE TABLE ProductFromSupplier ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), description VARCHAR(100), price FLOAT, keywords VARCHAR(40), supplier_id INT UNSIGNED);
CREATE TABLE DeliveryProduct ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), description VARCHAR(140), price FLOAT, amount INT, deliveryOrder_id INT UNSIGNED, origin_address VARCHAR(50) );
CREATE TABLE DeliveryOrder ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, destin_address VARCHAR(50));
ALTER TABLE ProductFromDrugstore ADD FOREIGN KEY (`drugstore_id`) REFERENCES Drugstore(id);
ALTER TABLE ProductFromSupplier ADD FOREIGN KEY (`supplier_id`) REFERENCES Supplier(id);
ALTER TABLE DeliveryProduct ADD FOREIGN KEY (`deliveryOrder_id`) REFERENCES DeliveryOrder(id);

 CREATE TABLE SupplierOrder ( id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, supplier_id INT UNSIGNED, product_name VARCHAR(50), product_price FLOAT, amount INT, payed FLOAT, FOREIGN KEY (supplier_id) REFERENCES Supplier(id) ON DELETE CASCADE);