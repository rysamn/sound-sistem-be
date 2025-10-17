# Tahap 1: Build aplikasi
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy semua file project
COPY . .

# Jalankan build tanpa test
RUN mvn clean package -DskipTests

# Tahap 2: Jalankan aplikasi
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy hasil build dari tahap sebelumnya
COPY --from=build /app/target/*.jar app.jar

# Buka port Spring Boot (default 8080)
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
