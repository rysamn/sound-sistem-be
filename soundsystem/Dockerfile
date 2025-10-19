# =====================================
# 🏗️ STAGE 1: BUILD (Maven + JDK 17)
# =====================================
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml dan pre-download dependencies (agar cache efisien)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy seluruh source code dan build JAR tanpa test
COPY src ./src
RUN mvn clean package -DskipTests

# =====================================
# 🚀 STAGE 2: RUN (Java Runtime)
# =====================================
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy hasil build dari stage sebelumnya
COPY --from=builder /app/target/*.jar app.jar

# Expose port aplikasi
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
