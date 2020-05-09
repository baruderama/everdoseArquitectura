# Delivery Microservice

## Endpoints

- **Deliver**

## Tables

### DeliveryProduct

| Field            | Type             | Null | Key | Default | Extra          |
|------------------|------------------|------|-----|---------|----------------|
| id               | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| name             | varchar(50)      | YES  |     | NULL    |                |
| description      | varchar(140)     | YES  |     | NULL    |                |
| PRICE            | float            | YES  |     | NULL    |                |
| amount           | int(11)          | YES  |     | NULL    |                |
| deliveryOrder_id | int(10) unsigned | YES  | MUL | NULL    |                |
| origin_address   | varchar(50)      | YES  |     | NULL    |                |


### DeliveryOrder

| Field          | Type             | Null | Key | Default | Extra          |
|----------------|------------------|------|-----|---------|----------------|
| id             | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
<<<<<<< HEAD
| destin_address | varchar(50)      | YES  |     | NULL    |                |
=======
| destin_address | varchar(50)      | YES  |     | NULL    |                |
>>>>>>> a48a75fb099ac92cfd74ad493b61488043764b35
