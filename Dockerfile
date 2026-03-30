# Dockerfile (racine backend)

FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml .
COPY common/pom.xml common/
COPY module-user/pom.xml module-user/
COPY module-task/pom.xml module-task/
COPY application/pom.xml application/

RUN mvn dependency:go-offline -B

COPY common/ common/
COPY module-user/ module-user/
COPY module-task/ module-task/
COPY application/ application/

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /build/application/target/*.jar app.jar

EXPOSE 8080

# Profil preprod pour Docker (pas preprod ni prod)
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=preprod", "app.jar"]