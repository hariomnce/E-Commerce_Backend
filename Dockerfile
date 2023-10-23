FROM openjdk:17
EXPOSE 8080
ADD target/ecom.jar ecom.jar
ENTRYPOINT ["java" ,"-jar","/ecom.jar"]
