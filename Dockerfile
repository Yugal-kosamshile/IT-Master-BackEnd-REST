FROM jelastic/maven:3.9.5-openjdk-21 AS build 
COPY  . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /target/OnlineCourseSystem-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]