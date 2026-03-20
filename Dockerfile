# Utilise une image JRE légère (suffisante pour l'exécution)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copie le JAR que tu as généré localement vers l'image
# Note : le chemin correspond à la structure multi-module
COPY application/target/*.jar app.jar

# Port par défaut de Spring Boot
EXPOSE 8080

# Commande de lancement
# On force le profil 'preprod' pour utiliser PostgreSQL sur Render
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=preprod", "app.jar"]