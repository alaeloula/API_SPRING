FROM openjdk:21
EXPOSE 8080
ADD target/quize.jar quize.jar
ENTRYPOINT ["java","-jar","/quize.jar"]

