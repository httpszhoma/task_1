# Используем официальный образ Java как базовый
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл JAR из вашего проекта в контейнер
COPY target/practice_1-0.0.1-SNAPSHOT.jar /app/practice_1.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "practice_1.jar"]

