# Supplier Microservice

## Endpoints


- **AddSupplier**
- **AddProductToSupplier** (temporal)
- **AddSupplier**
- **OrderFromSupplier**
- **PaySuppliers**

## Tables

## Supplier

| Field   | Type             | Null | Key | Default | Extra          |
|---------|------------------|------|-----|---------|----------------|
| id      | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| name    | varchar(50)      | YES  |     | NULL    |                |
| address | varchar(50)      | YES  |     | NULL    |                |
| phone   | varchar(15)      | YES  |     | NULL    |                |
| email   | varchar(20)      | YES  |     | NULL    |                |
| uri     | varchar(100)     | YES  |     | NULL    |                |

## ProductFromSupplier

| Field       | Type             | Null | Key | Default | Extra          |
|-------------|------------------|------|-----|---------|----------------|
| id          | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| name        | varchar(50)      | YES  |     | NULL    |                |
| description | varchar(100)     | YES  |     | NULL    |                |
| price       | float            | YES  |     | NULL    |                |
| keywords    | varchar(40)      | YES  |     | NULL    |                |
| supplier_id | int(11)          | YES  |     | NULL    |                |

## SupplierOrder

| Field         | Type             | Null | Key | Default | Extra          |
|---------------|------------------|------|-----|---------|----------------|
| id            | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| supplier_id   | int(10) unsigned | YES  | MUL | NULL    |                |
| product_name  | varchar(50)      | YES  |     | NULL    |                |
| product_price | float            | YES  |     | NULL    |                |
| amount        | int(11)          | YES  |     | NULL    |                |
| payed         | float            | YES  |     | NULL    |                |
