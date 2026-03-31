# ─────────────────────────────────────────
# Étape 1 : Build Maven
# ─────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /build

# Copie du pom parent et des poms modules pour profiter du cache Maven
COPY pom.xml .
COPY common/pom.xml common/
COPY module-user/pom.xml module-user/
COPY module-task/pom.xml module-task/
COPY application/pom.xml application/

# Téléchargement des dépendances (cacheable)
RUN mvn dependency:go-offline -B

# Copie des sources
COPY common/src common/src
COPY module-user/src module-user/src
COPY module-task/src module-task/src
COPY application/src application/src

# Build sans tests (les tests seront lancés en CI)
RUN mvn clean package -DskipTests -B

# ─────────────────────────────────────────
# Étape 2 : Image finale légère
# ─────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copie du jar buildé
COPY --from=builder /build/application/target/*.jar app.jar

# Port exposé (Spring écoute sur 8080)
EXPOSE 8080

# Profil injecté depuis docker-compose via SPRING_PROFILES_ACTIVE
ENTRYPOINT ["java", "-jar", "app.jar"]