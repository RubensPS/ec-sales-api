FROM openjdk:18

COPY ./build/libs/ec-sales-api-0.0.1-SNAPSHOT.jar ec-sales-api-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","ec-sales-api-0.0.1-SNAPSHOT.jar"]