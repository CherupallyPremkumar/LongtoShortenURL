FROM openjdk:17
EXPOSE 8080
ADD target/appURL.jar appURL.jar
ENTRYPOINT ["java", "-jar","appURL.jar","--spring.config.location=classpath:/application-docker.properties"]
