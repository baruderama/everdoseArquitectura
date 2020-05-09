# Users Microservice

## Endpoinds

- **GetToken**
- **CheckToken**
- **UserServlet**

## Tables

### User

| Field    | Type         | Null | Key | Default | Extra |
|----------|--------------|------|-----|---------|-------|
| username | varchar(50)  | NO   | PRI | NULL    |       |
| name     | varchar(100) | YES  |     | NULL    |       |
| email    | varchar(50)  | YES  |     | NULL    |       |
| password | varchar(20)  | YES  |     | NULL    |       |

