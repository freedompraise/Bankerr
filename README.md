# Bank Account API
This is a sample project for a Bank Account API built using Spring Boot and Axon Framework.

## Getting Started

### Perequisites
- Java 11 or later
- Maven 3.6 or later

### Building the Project
To build the project, run the following command in the root directory  of the project:

```sh 
mvn clean install 
```

### Running the Application
To run the application, you can use the following command:
```sh
mvn spring-boot:run
```
This will start the application on port `8080` by default. You can access the API documentation by visiting http://localhost:8080/swagger-ui.html in your browser. 

### Running Tests
To run the test, use the followung command:
```sh
mvn test 
```
## API Endpoints
THe following API endpoints are available:
###Create an Account
**` POST/ accounts`**
Create a new bank account

Request Body:
```
{
  "customerId": "string"
}
```
### Credit an Account
**`POST /accounts/{accountId}/credit`**

Credit the specified bank account.

Path Variables:

**`accountId`**: The ID of the account to credit.
Request Body:
```
{
  "amount": 0
}
```
### Debit an Account
`POST /accounts/{accountId}/debit`

Debit the specified bank account.

Path Variables:

**`accountId`**: The ID of the account to debit.
Request Body:
```
{
  "amount": 0
}
```
## Built With
- Spring Boot - Web framework for building RESTful APIs
- Axon Framework - CQRS and event sourcing framework
- Swagger - API documentation tool

## Authors
<a href="#github.com/freedompraise" >Praise Freedom Dike</a>

## License
This project is licensed under the MIT License - see the <a href="https://chat.openai.com/chat/LICENSE">LICENSE file for details.</a>




