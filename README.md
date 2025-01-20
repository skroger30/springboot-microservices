# springboot-microservices

Implementing micro-services architecture using spring-boot.

Spring-boot version : 3.3.7
Java version : 17
DB : MySQL workbench 8


	• Created two services named EmployeeService and DepartmentService.
	• Added ModelMapping Functionality for mapping JPA entity to DTO and vice-versa.
	•        org.modelmapper version :3.1.0
	• Added Exception Handling at global level for specific and general exceptions.
	• Synchronous Communication Implementation using -
		RestTemplate, WebClient, OpenFeign
	• Added Service-Registry : Eureka Server
	• Added existing services as clients. 
	• Load-balancing : running multiple instances of same services with dynamic API calling.
	• Implemented API Gateway 
	• Implemented Centralized Repo to register configs of all the services at one location.


