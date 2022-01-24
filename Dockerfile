FROM maven:3.8.4-openjdk-17-slim as builder
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:11-slim
WORKDIR /home/app
COPY --from=builder /home/app/target/crud-with-spring-rest-0.0.1-SNAPSHOT.jar ./
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
RUN mkdir documents
RUN mv crud-with-spring-rest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000
CMD ["java", "-jar", "./app.jar"]