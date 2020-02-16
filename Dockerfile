FROM openjdk:8
ADD /target/QuoteManipulator-0.0.1-SNAPSHOT.jar QuoteManipulator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","QuoteManipulator-0.0.1-SNAPSHOT.jar"]