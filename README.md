# EMI Calculator

This project implements a simple EMI (Equated Monthly Installment) calculator API.

## Features

- Calculate EMI based on the given inputs: loan value, yearly interest rate and loan term.

## Usage

To calculate EMI for a loan, make a `POST` request to the `/calculate-emi` endpoint with the following parameters:

- `loanValue`: The loan value .
- `yearlyInterestRate`: The yearly interest rate in percentage (from 0 to 100)
- `loanTerm`: The loan term in years.

#### Example request

<details>
 <summary><code>POST</code> <code><b>/api/v1/calculate-emi</b></code> <code>

##### Parameters

> | name               |  type     | data type   | description                                            |
> |--------------------|-----------|-------------|--------------------------------------------------------|
> | loanValue          |  required | Long        | The loan value                                         |
> | yearlyInterestRate |  required | Double      | The yearly interest rate in percentage (from 0 to 100) |
> | loanTerm           |  required | Integer     | The loan term in years.                                |


##### Responses

> | http code | content-type                  | response                                                                       |
> |-----------|-------------------------------|--------------------------------------------------------------------------------|
> | `200`     | `application/json`            | `{"emi": 776.85}`                                                              |
> | `400`     | `application/json`            | `{ "statusCode": 400, "errors": [ "The loan term should be lesser than 30"] }` |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" --data '{ "loanValue": 100200, "yearlyInterestRate": 7, "loanTerm": 20 }' http://localhost:8080/api/v1/calculate-emi
> ```
</details>

## Dependencies

- Java 17 or higher
- Spring Boot
- Maven (for building the project)

## Run the project

- Build the project running `mvn clean install`
- Run the project running `mvn spring-boot:run`

