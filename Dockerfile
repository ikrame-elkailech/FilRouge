FROM openjdk:17-jdk

WORKDIR /app

COPY target/FilRouge-0.0.1-SNAPSHOT.jar /app/FilRouge.jar

EXPOSE 8080

CMD ["java", "-jar" ,"labxpert.jar"]