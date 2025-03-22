FROM openjdk:21
COPY target/emailai-0.0.1-SNAPSHOT.jar /app/myapp.jar
WORKDIR /app/

EXPOSE 8087

CMD ["java", "-jar", "/app/myapp.jar"]