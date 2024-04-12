FROM maven:3.8.1-openjdk-17
WORKDIR /app
COPY . /app
RUN mvn clean compile
CMD ["mvn", "exec:java"]
