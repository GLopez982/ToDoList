FROM eclipse-temurin:23-jdk

# The /app directory should act as the main application directory
WORKDIR /app

# Copy the project files to the /app directory
COPY . .

# Change to the source directory
WORKDIR /app/src/main/java/com/lopez

# Compile the Java application
RUN javac *.java

# Run the Java application
CMD ["java", "-cp", "/app/src/main/java", "com.lopez.App"]