# QuoteManipulator

## Test spring project that displays quotes 

Running the application:
```
mvn clean install

docker-compose build

docker-compose up
```

### Program evolution stages:

1) Created pure spring MVC project with controller and service layer 
2) Added dao layer with hardcoded data
3) Added CRUD request handling controller methods
4) Added service layer integration testing using reflection and dependency injection 
5) Added controller layer integration testing with MockMVC and Mockito 
6) Added class level documentation
7) Converted pure spring MVC project to spring boot project
8) Added spring boot starter actuator endpoint (http://localhost:8080/actuator/health ; Sensitive endpoints not exposed if web security is not enabled)
9) Switched to intellij IDE in linux virtual machine
10) Added Dockerfile and docker-compose.yml file - successful dockerizing
11) Added author and rating to quote model
12) Added spring data jpa, removed hardcoded dao layer and added db integration tests
13) Added filters for main endpoint - /quotes
14) Added monitoring support - prometheus(on port 9090) and grafana(on port 3000) 
15) Added source field to entity 
16) Added dev and prod profiles
17) Added mysql database for prod profile. H2 is still used for dev profile
18) Added ELK stack - elasticsearch, logstash, kibana and transferring logs via tcp (running elk requires command "sudo sysctl -w vm.max_map_count=262144")