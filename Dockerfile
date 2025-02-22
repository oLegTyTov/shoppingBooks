# 1. Використовуємо OpenJDK 17 як середовище для білду
FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app

# 2. Копіюємо весь код у контейнер
COPY . .

# 3. Виконуємо білд (створення JAR-файлу)
RUN mvn clean package -DskipTests

# 4. Використовуємо OpenJDK 17 для запуску програми
FROM openjdk:17-jdk-slim
WORKDIR /app

# 5. Копіюємо зібраний JAR-файл із попередньої стадії
COPY --from=build /app/target/shoppingbooks-0.0.1-SNAPSHOT.jar app.jar

# 6. Запускаємо додаток
CMD ["java", "-jar", "app.jar"]
