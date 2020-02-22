# QuoteManipulator

## Test spring project that displays quotes 

### Program evolution stages:

1) Created pure spring MVC project with controller and service layer 
2) Added dao layer with hardcoded data
3) Added CRUD request handling controller methods
4) Added service layer integration testing using reflection and dependency injection 
5) Added controller layer integration testing with MockMVC and Mockito 
6) Added class level documentation
7) Converted pure spring MVC project to spring boot project
8) Added spring boot starter actuator endpoint (http://localhost:8080/actuator/health ; Sensitive endpoints disabled if web security is not enabled)
9) Switched to intellij IDE in linux virtual machine
10) Added Dockerfile and docker-compose.yml file - successful dockerizing
11) Added author and rating to quote model
12) Added spring data jpa and removed hardcoded dao layer
