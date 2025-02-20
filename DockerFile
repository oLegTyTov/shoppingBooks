# Використовуємо офіційний образ JDK 17
FROM openjdk:17-jdk-slim

# Встановлюємо робочу директорію всередині контейнера
WORKDIR /app

# Копіюємо зібраний JAR-файл у контейнер
COPY target/shoppingbooks-0.0.1-SNAPSHOT.jar app.jar

# Виставляємо порт для контейнера
EXPOSE 8080

# Команда для запуску застосунку
ENTRYPOINT ["java", "-jar", "app.jar"]
