# Everdose

<h1 align="center">Microservices</h1>

<h2 align="center">Stock</h2>

### Add new product

> Save the product information in the stock.

#### Request
* name: Name of the product.
* key_words: Key words related to the product.
* amount: Total amount to add, it can be the number of boxes, bottles, etc...
* threshold: Minimum threshold, if reached, the supplier will be contacted.
* location: PHAR. STOCK

<details>
 <summary>Example</summary>

 ```json
 {
 name: "levonorgestrel",
 key_words: "levonorgestrel", "1.5mg",
 amount: 0,
 threshold: 40,
 location: STOCK
 }
 ```
</details>


#### Response
* status: OK, ERROR_CODE.
* id: Generated id of the product.

### Remove product

> Removes the desired amount of the product

#### Request
* id: Id of the product to remove.
* amount: The desirable amount to remove. If 0, removes the product.

<details>
 <summary>Example</summary>

 ```json
 {
   id: "LEVONORGESTREL_1P5MG",
   amount: 4
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE

### Catalog

> Lists the full catalog of products.

#### Request

* key_words: List of words for searching into the catalog. _Optional_
* sublist: Pair of indexes that define sublist of products. _Optional_

<details>
 <summary>Example</summary>

 ```json
 {
   key_words: ["jabón","avena"],
   sublist: [0,5]
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE
* list of products

<details>
 <summary>Example</summary>

 ```json
  {
    products:  [
       {
         id: "JABONDOVEAV90GR",
         name: "Jabón de Avena",
         image: "link_to_the_image",
         price: 9000,
         measure: "90gr",
         description: "Bla, bla, bla",
       },
       ...
     ]
  }
 ```
</details>

### Running out

> Looks for the products which are running out and supply them.

#### Request

* product_id: Id of the product that needs to be supplied. _Optional_

#### Response

* List of ids of the products which were running out but were asked for supply.
<details>
 <summary>Example</summary>

 ```json
  {
    products:  [
       {
         id: "DIFENHIFS150MG"
       },
       ...
     ]
  }
 ```
</details>

<h2 align="center">Suppliers</h2>

### Add supplier

#### Request
- name:
- address:
- phone:
- email:
- uri:

<details>
 <summary>Example</summary>

 ```json
 {
   name: "Farmatodo",
   address: "Cra 1 # 2 - 40, Sopó",
   phone: "7398276",
   email: "supply@pharmatodo.com"
   uri: "https://farmatodo/api/"
 }
 ```
</details>

#### Response
* id: Generated id of the supplier.
* status: OK, ERROR_CODE

### Add supplier financial info

#### Request
* supplier_id: Id of the supplier.
* type: BANK.
* information: Information depending of the type.

<details>
 <summary>Example</summary>

 ```json
 {
   user_id: "89023809",
   type: "BANK",
   information: {
     bank: "BANCOLOMBIA",
     account_holder: "NAME LAST NAME",
     account_number: "2973223",
   }
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE

### Add drugstore

#### Request
- name:
- address:
- phone:
- email:
- uri:

<details>
 <summary>Example</summary>

 ```json
 {
   name: "Farmatodo",
   address: "Cra 1 # 2 - 40, Sopó",
   phone: "7398276",
   email: "supply@pharmatodo.com"
   uri: "https://farmatodo/api/"
 }
 ```
</details>

#### Response
* id: Generated id of the drugstore.
* status: OK, ERROR_CODE

### Order

> Orders the desired amount of the products listed. If okay, the order is saved as pending payment.

#### Request
- products:
  - product_id: Id of the product that is running out.
  - amount: The desirable amount to order.
  - key_words: Key words describing the product.

<details>
 <summary>Example</summary>

 ```json
 {
   products:[
     {
       product_id: "JABONDOVEAV90GR",
       key_words: "jabón,dove,90gr,woman",
       amount: 100,
     },
     ...
   ]
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE

### Pay suppliers

> Pays all the suppliers with pending payment and changes their states.

#### Request
Not needed

#### Response
* status: OK, ERROR_CODE

<h2 align="center">Payments</h2>

### Charge

#### Request
* type: CARD, DEBIT.
* amount: The desirable amount to charge.
* info: Additional information, depending on the type.

<details>
 <summary>Example</summary>

 ```json
 {
   type: "CARD",
   amount: 20000,
   description: "Compra Pharmate",
   payment_info: {
     card_holder: "NAME LAST NAME",
     card_number: "4657382973223",
     expires: "30/24",
     ccv: 234,
   }

 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE

### Pay

#### Request
* type: BANK.
* amount: The desirable amount to pay.
* info: Additional information, depending on the type.

<details>
 <summary>Example</summary>

 ```json
 {
   type: "BANK",
   bank: "BANCOLOMBIA",
   amount: 280000,
   description: "Pago proveedores",
   payment_info: {
     account_holder: "NAME LAST NAME",
     account_number: "2973223",
   }

 }
 ```
</details>

#### Response
* status: OK, INPROGRESS, ERROR_CODE

<h2 align="center">Delivery</h2>

### Order from drugstore

#### Request
- id_drugstore: Id of the drugstore.
- address_to: Address where the product will be find.
- products: List of products
  - id: Id of the product
  - key_words: List of key words.

<details>
 <summary>Example</summary>

 ```json
 {
   id_drugstore: "FARMATODO54",
   address_to: "Calle 56 # 29 - 10",
   products: [
     {
       id: "DIFENHIFS150MG",
       key_words: "difenhidramina,150mg"
     },
     {
       id: "AMITRIPTIL89",
       key_words: "amitriptilina,150mg"
     }
   ]
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE
* id: Id of the order to query later.

<details>
 <summary>Example</summary>

 ```json
 {
   status: "OK",
   id: "1162671357"
 }
 ```
</details>

### Order from stock

#### Request
- address: address to deliver
- products: List of products.
  - id: Id of the product.

<details>
 <summary>Example</summary>

 ```json
 {
   address: "CRA  34 # 56 - 43. FARMATODO",
   products: [
     {
       id: "DIFENHIFS150MG"
     },
     {
       id: "AMITRIPTIL89"
     }
   ]
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE

### Get state of an order

#### Request
* order_id: Id of the order to query.

<details>
 <summary>Example</summary>

 ```json
 {
   order_id: "1162671357",
 }
 ```
</details>

#### Response
* state: ASSIGNED, ON_THE_WAY, DELIVERED, ERROR


<h2 align="center">Users</h2>


### Add user

#### Request
* id: National id or unique id.
* name: Name of the client.
* surname: Last name of the client.
* phone: Phone of the client.
* email: Email of the client.

<details>
 <summary>Example</summary>

 ```json
 {
   id: "89023809",
   name: "JUVENAL",
   surname: "URBINO",
   phone: "7551929",
   email: "jurbino@gmail.com"
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE.

### Add financial info to user

#### Request
* payment_info: { type, information }

<details>
 <summary>Example</summary>

 ```json
 {
   user_id: "89023809",
   type: "CARD",
   information: {
     type: "VISA"
     number: 435354545,
     ccv: 342,
     ...
   }
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE

### Remove user

#### Request
* username: Username to remove
<details>
 <summary>Example</summary>

 ```json
 {
   username: "cutepussy"
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE

### Authenticate

#### Request
* username:
* password:

<details>
 <summary>Example</summary>

 ```json
 {
   username: "cutepussy",
   password: "Frambuesa1"
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE
* User information
<details>
 <summary>Example</summary>

 ```json
 {
   user_id: "",
 }
 ```
</details>

<h2 align="center">Emails</h2>

### Request product to supplier

#### Request
* username:

<details>
 <summary>Example</summary>

 ```json
 {
   username: "cutepussy",
   password: "Frambuesa1"
 }
 ```
</details>

#### Response
* status: OK, ERROR_CODE
* User information
<details>
 <summary>Example</summary>

 ```json
 {
   user_id: "",
 }
 ```
</details>

<h1 align="center">External</h1>

<h2 align="center">Supplier</h2>

## Get information about products

#### Request

- id: Id of the product. __Optional__
- key_words: Key words, separated by space. __Optional__
- sortby: lowprice, highprice, bestmatch.

<details>
 <summary>Example</summary>

 ```bash
 curl "http://farmatodo.com/api?key_words=jabon+dove+avena&sortby=lowprice"
 ```
</details>

#### Response

- products: List of products.
  - id: Id of the product.
  - name:
  - image: Link of the image.
  - price: Price of the product.
