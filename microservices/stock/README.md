# Stock Microservice

## Endpoinds

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
