# Stock Microservice

## Endpoinds

- **AddDrugstore**
- **AddProductToDrugstore**
- **OrderFromDrugstore**
- **AddProduct**
  - Parameters
    - name
    - keywords
    - description
    - price 
    - location
    - image
    - threshold
    - amount
  
- **Catalog**
  
- **RemoveProduct**
  - Parameters
    - id

- **CheckRunningOut**

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
| image        | varchar(200)     | YES  |     | NULL    |                |

### Product

| Field       | Type         | Null | Key | Default | Extra          |
|-------------|--------------|------|-----|---------|----------------|
| id          | int(11)      | NO   | PRI | NULL    | auto_increment |
| name        | varchar(50)  | YES  |     | NULL    |                |
| description | varchar(100) | YES  |     | NULL    |                |
| location    | varchar(10)  | YES  |     | NULL    |                |
| price       | float        | YES  |     | NULL    |                |
| threshold   | int(11)      | YES  |     | NULL    |                |
| amount      | int(11)      | YES  |     | NULL    |                |
| image       | varchar(200) | YES  |     | NULL    |                |
| keywords    | varchar(40)  | YES  |     | NULL    |                |
