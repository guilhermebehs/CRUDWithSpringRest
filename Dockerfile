FROM maven:3.6.1-jdk-11-slim
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
RUN mkdir documents
RUN mvn install
EXPOSE 3000
CMD ["mvn", "spring-boot:run"]