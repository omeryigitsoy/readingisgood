<h1> Reading is Good Setup Instructions</h1>
This project developed by using microservice architecture.
There are 7 different services exist.

Please follow instructions to run project:

1)Clone project using https://github.com/omeryigitsoy/readingisgood.git<br>

<h2>Follow below steps inside project directory</h2>

<h3>Eureka Server Setup:</h3>
cd eureka-server<br>
mvn -DskipTests=true package<br>
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar

<h3>Config Server Setup:</h3>
cd config-server<br>
mvn -DskipTests=true package<br>
java -jar target/config-server-0.0.1-SNAPSHOT.jar

<h3>API Gateway Setup:</h3>
cd api-gateway<br>
mvn -DskipTests=true package<br>
java -jar target/api-gateway-0.0.1-SNAPSHOT.jar

<h3>Customer Service Setup:</h3>
cd customer-service<br>
mvn -DskipTests=true package<br>
java -jar target/customer-service-0.0.1-SNAPSHOT.jar

<h3>Book Service Setup:</h3>
cd book-service<br>
mvn -DskipTests=true package<br>
java -jar target/book-service-0.0.1-SNAPSHOT.jar

<h3>Order Service Setup:</h3>
cd order-service<br>
mvn -DskipTests=true package<br>
java -jar target/order-service-0.0.1-SNAPSHOT.jar

<h3>Statistic Service Setup:</h3>
cd statistic-service<br>
mvn -DskipTests=true package<br>
java -jar target/statistic-service-0.0.1-SNAPSHOT.jar

<h2>Summary of project usage</h2>

You can call related service using api gateway project which works on 8080 port.
Example scenerios:

<h4>Creating New Customer:<h4>
Post Request: localhost:8080/customer<br>
{
"firstName":"customer1",
"lastName":"customerLastName",
"email":"customer@gmail.com",
"password":"111111"
}

<h4>Login Customer:</h4><br>
Post Request: localhost:8080/customer/login<br>
{
"email":"customer@gmail.com",
"password":"111111"
}

After successfull login server returns token information for accessing secured services.
You need to send this Bearer token as a header while making request to other services.

<h4>Add new book to system:<h4>
Post Request:localhost:8080/book<br>
{
"name":"Book1",
"author":"Author 1",
"stockCount":100,
"price":500
}

<h4>Update Book Stock <h4>
POST Request :<br> 
localhost:8080/book/bookId/stock/newStockCount

<h4> Query All Orders of Customer
Get Request:
localhost:8080/customer/customerOrders/{customerId}

Get Order detail by OrderId:
Get Request : localhost:8080/order/{orderId}

New Order
Post Request:
localhost:8080/order/
{
"customerId":1,
"bookId":1,
"amount":148,
"count":1
}

You can retrieve monthly statistics using below url:<br>
GET Request http://localhost:8080/statistic/customerId<br>

Also you can find swagger documentation of each microservice below:<br>
http://localhost:8001/swagger-ui/index.html<br>
http://localhost:8002/swagger-ui/index.html<br>
http://localhost:8003/swagger-ui/index.html<br>
http://localhost:8004/swagger-ui/index.html<br>
