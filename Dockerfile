# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 as builder

WORKDIR /app

# Copy project files
COPY build.xml .
COPY manifest.mf .
COPY nbproject/ ./nbproject/
COPY src/ ./src/
COPY lib/ ./lib/

# Build the project using Ant (as per build.xml)
RUN apt-get update && apt-get install -y ant && rm -rf /var/lib/apt/lists/*
RUN ant build

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR from builder
COPY --from=builder /app/dist/*.jar app.jar

# Expose port (adjust based on your REST API port)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
