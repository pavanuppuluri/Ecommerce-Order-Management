**Ecommerce-Order-Management**
This repo contains the code for Order Management.

In every Ecommerce application, Order creation/updation/searching/deletion exists.
In this repo, I wrote code which perform functionalities like -

Order creation
Order updation
Getting Order details
Order deletion

**To use this repo please follow below steps**

1. Clone https://github.com/pavanuppuluri/Ecommerce-Order-Management.git
2. You can setup mysql locally. Steps given in **mysql_local_setup_steps.doc**
3. Now run the Spring Boot Application


**Endpoint to create an order**

http://localhost:8080/createorder

Method:           POST

Sample Payload:   **Order_Create_Payload.txt**


**Endpoint to update an order**

http://localhost:8080/createorder


Method:           POST

Sample Payload:   **Order_Update_Payload.txt**

**Endpoint to get order details**

http://localhost:8080/order

Method:           GET

Input :           ID

**Endpoint to delete an order**

http://localhost:8080/deleteorder

Method:           DELETE

Input :           ID


Happy Coding!!!

