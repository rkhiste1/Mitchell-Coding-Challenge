# Mitchell-Coding-Challenge
How To Run:
  •	Clone this repository
  •	Make sure you are using JDK 1.8 and Maven 3.x
  •	You can build the project and run the tests by running below command inside directory vechile-rest-service:
    mvn clean install
  •	Once successfully built, you can run the service by:
    mvn spring-boot:run
  •	The above command will boot the application like below: 
    2020-02-04 10:53:37.765  INFO 10872 --- [main] c.r.v.VehicleRestServiceApplication: Started VehicleRestServiceApplication in 4.166 seconds (JVM running for 4.73)
  •	After it is started successfully, the service would be up and running and can be accessed by any of the following :
    1.	Test client can be accessed on: http://localhost:8080/index.html
    2.	REST endpoints can be accessed as below:
    GET: http://localhost:8080/vehicleservice/vehicles
    GET: http://localhost:8080/vehicleservice/vehicles/{id}
    POST: http://localhost:8080/vehicleservice/vehicles
    PUT: http://localhost:8080/vehicleservice/vehicles
    DELETE: http://localhost:8080/vehicleservice/vehicles/{id}
