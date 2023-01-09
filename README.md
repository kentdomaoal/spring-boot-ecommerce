# spring-boot-ecommerce

Sample E-Commerce Implementation with Spring Boot and Angular.

The backend consist of REST APIs using Spring Web + Spring Data JPA, while front-end utilized Angular.

Includes tests using TestRestTemplate and Cucumber.

[//]: # (Data-driven using Excel, CSV, JSON file.)

[//]: # ([Github actions]&#40;https://github.com/kentdomaoal/restapi-testing-serenity-cucumber/actions&#41; were setup for its CI/CD Pipeline.)

---
## How to run in local machine

## 💻 *Pre-requisites*
- [![Git](https://img.shields.io/badge/-Git-F05032?style=flat&logo=git&logoColor=FFFFFF)](https://git-scm.com/downloads)
- [![Java 8](https://img.shields.io/badge/-Java%208-red?style=flat&logo=java&logoColor=FFFFFF)](https://www.oracle.com/ph/java/technologies/downloads) or higher
- [![Maven](https://img.shields.io/badge/-Maven-C71A36?style=flat&logo=apache-maven&logoColor=FFFFFF)](https://maven.apache.org/download.cgi)

### Clone the project
```
git clone --recursive https://github.com/kentdomaoal/spring-boot-ecommerce.git
```
``` 
cd spring-boot-ecommerce
```
---
### Running the test
``` 
mvn clean install
``` 

### View html report
After test execution, you will see a section like this on your console.

![cucumber_report.png](cucumber_report.png)


Alternatively, Cucumber Report can be found on this directory on your project root.

      /target/cucumber-reports/Cucumber.html

---
### Running the Spring Boot application (Back-end REST APIs)
Expecting you are in the Spring project root directory.

Run below command.
```
mvn spring-boot:run
```


### Running the Angular application (Front-end)
Open a new terminal.

From the Spring project root directory, go to the directory of the Angular application root.
```
cd src\main\js\ecommerce
```
Install dependencies
```
npm install
```
Wait until all dependencies are installed.

Then, start the application.
```
npm start
```

Wait until application is fully up and running, you can now access the application on 
http://localhost:4200/

