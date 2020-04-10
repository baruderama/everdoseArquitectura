## Endpoints

- **AddDrugstore**
- **AddProductToDrugstore**
- **AddSupplier**
- **AddProductToSupplier** (temporal)
- **AddSupplier**
- **OrderFromDrugstore**
- **OrderFromSupplier**
- **PaySuppliers**

## Tables

### Drugstore

| Field   | Type             | Null | Key | Default | Extra          |
|---------|------------------|------|-----|---------|----------------|
| id      | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| name    | varchar(50)      | YES  |     | NULL    |                |
| address | varchar(50)      | YES  |     | NULL    |                |
| phone   | varchar(15)      | YES  |     | NULL    |                |
| email   | varchar(20)      | YES  |     | NULL    |                |
| uri     | varchar(100)     | YES  |     | NULL    |                |


### ProductFromDrugstore

| Field        | Type             | Null | Key | Default | Extra          |
|--------------|------------------|------|-----|---------|----------------|
| id           | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| name         | varchar(50)      | YES  |     | NULL    |                |
| description  | varchar(100)     | YES  |     | NULL    |                |
| price        | float            | YES  |     | NULL    |                |
| keywords     | varchar(40)      | YES  |     | NULL    |                |
| drugstore_id | int(11)          | YES  |     | NULL    |                |

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
|-------------|------------------|------|-----|---------|----------------
| id          | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| name        | varchar(50)      | YES  |     | NULL    |                |
| description | varchar(100)     | YES  |     | NULL    |                |
| price       | float            | YES  |     | NULL    |                |
| keywords    | varchar(40)      | YES  |     | NULL    |                |
| supplier_id | int(11)          | YES  |     | NULL    |                |

