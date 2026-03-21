# Image officielle Maven + JDK pour build + JRE pour runtime (multi-stage)
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

# Copie les sources Maven (pom.xml + modules)
COPY pom.xml .
COPY common/pom.xml common/
COPY module-user/pom.xml module-user/
COPY module-task/pom.xml module-task/
COPY application/pom.xml application/

# Télécharge les dépendances (cache layer)
RUN mvn dependency:go-offline -B

# Copie le code source
COPY common/ common/
COPY module-user/ module-user/
COPY module-task/ module-task/
COPY application/ application/

# Build final (skip tests pour vitesse)
RUN mvn clean package -DskipTests

# Stage runtime (léger)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copie le JAR généré par Maven
COPY --from=builder /build/application/target/*.jar app.jar

EXPOSE 8080

# Force profil prod (ou preprod si tu préfères)
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]