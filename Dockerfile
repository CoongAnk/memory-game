# Sử dụng image JDK 17 chính thức
FROM eclipse-temurin:17-jdk-alpine

# Tạo thư mục trong container
WORKDIR /app

# Copy file pom.xml và tải dependencies trước
COPY pom.xml ./
RUN ./mvnw dependency:go-offline -B || true

# Copy toàn bộ project và build
COPY . ./
RUN ./mvnw clean package -DskipTests

# Run file JAR
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/memory-game-0.0.1-SNAPSHOT.jar"]
